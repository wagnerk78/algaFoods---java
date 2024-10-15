package com.wagner.kroiss.api.v1.controller;

import com.wagner.kroiss.api.v1.assembler.PedidoInputDisassembler;
import com.wagner.kroiss.api.v1.assembler.PedidoModelAssembler;
import com.wagner.kroiss.api.v1.assembler.PedidoResumoModelAssembler;
import com.wagner.kroiss.api.v1.model.PedidoModel;
import com.wagner.kroiss.api.v1.model.PedidoResumoModel;
import com.wagner.kroiss.api.v1.model.input.PedidoInput;
import com.wagner.kroiss.api.v1.openApi.controller.PedidoControllerOpenApi;
import com.wagner.kroiss.core.data.PageWrapper;
import com.wagner.kroiss.core.data.PageableTranslator;
import com.wagner.kroiss.core.security.AlgaSecurity;
import com.wagner.kroiss.domain.exception.EntidadeNaoEncontradaException;
import com.wagner.kroiss.domain.exception.NegocioException;
import com.wagner.kroiss.domain.model.Pedido;
import com.wagner.kroiss.domain.model.Usuario;
import com.wagner.kroiss.domain.repository.PedidoRepository;
import com.wagner.kroiss.domain.filter.PedidoFilter;
import com.wagner.kroiss.domain.service.EmissaoPedidoService;
import com.wagner.kroiss.infrastructure.Specifications.PedidoSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping(path = "/v1/pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedidoController implements PedidoControllerOpenApi {


    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private EmissaoPedidoService emissaoPedido;

    @Autowired
    private PedidoModelAssembler pedidoModelAssembler;

    @Autowired
    private PedidoResumoModelAssembler pedidoResumoModelAssembler;

    @Autowired
    private PedidoInputDisassembler pedidoInputDisassembler;

    @Autowired
    private PagedResourcesAssembler<Pedido> pagedResourcesAssembler;

    @Autowired
    private AlgaSecurity algaSecurity;

    @GetMapping
    public PagedModel<PedidoResumoModel> pesquisar(PedidoFilter filtro,
                                                   @PageableDefault(size = 10) Pageable pageable) {
        Pageable pageableTraduzido = traduzirPageable(pageable);

        Page<Pedido> pedidosPage = pedidoRepository.findAll(
                PedidoSpecs.usandoFiltro(filtro), pageableTraduzido);

        pedidosPage = new PageWrapper<>(pedidosPage, pageable);

        return pagedResourcesAssembler.toModel(pedidosPage, pedidoResumoModelAssembler);
    }

    @GetMapping("/{codigo}")
    public PedidoModel buscar(@PathVariable String codigo) {
        Pedido pedido = emissaoPedido.buscarOuFalhar(codigo);

        return pedidoModelAssembler.toModel(pedido);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoModel adicionar(@Valid @RequestBody PedidoInput pedidoInput) {
        try {
            Pedido novoPedido = pedidoInputDisassembler.toDomainObject(pedidoInput);


            novoPedido.setCliente(new Usuario());
            novoPedido.getCliente().setId(algaSecurity.getUsuarioId());

            novoPedido = emissaoPedido.emitir(novoPedido);

            return pedidoModelAssembler.toModel(novoPedido);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    private Pageable traduzirPageable(Pageable apiPageable) {
        var mapeamento = Map.of(
                "codigo", "codigo",
                "restaurante.nome", "restaurante.nome",
                "nomeCliente", "cliente.nome",
                "valorTotal", "valorTotal"
        );

        return PageableTranslator.translate(apiPageable, mapeamento);
    }



}
