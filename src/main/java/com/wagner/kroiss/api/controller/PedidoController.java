package com.wagner.kroiss.api.controller;

import com.wagner.kroiss.api.assembler.PedidoInputDisassembler;
import com.wagner.kroiss.api.assembler.PedidoModelAssembler;
import com.wagner.kroiss.api.assembler.PedidoResumoModelAssembler;
import com.wagner.kroiss.api.model.PedidoModel;
import com.wagner.kroiss.api.model.PedidoResumoModel;
import com.wagner.kroiss.api.model.input.PedidoInput;
import com.wagner.kroiss.domain.exception.EntidadeNaoEncontradaException;
import com.wagner.kroiss.domain.exception.NegocioException;
import com.wagner.kroiss.domain.model.Pedido;
import com.wagner.kroiss.domain.model.Usuario;
import com.wagner.kroiss.domain.repository.PedidoRepository;
import com.wagner.kroiss.domain.repository.filter.PedidoFilter;
import com.wagner.kroiss.domain.service.EmissaoPedidoService;
import com.wagner.kroiss.infrastructure.Specifications.PedidoSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

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

    @GetMapping
    public List<PedidoResumoModel> pesquisar(PedidoFilter filtro) {
        List<Pedido> todosPedidos = pedidoRepository.findAll(PedidoSpecs.usandoFiltro(filtro));

        return pedidoResumoModelAssembler.toCollectionModel(todosPedidos);
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

            // TODO pegar usuário autenticado
            novoPedido.setCliente(new Usuario());
            novoPedido.getCliente().setId(1L);

            novoPedido = emissaoPedido.emitir(novoPedido);

            return pedidoModelAssembler.toModel(novoPedido);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }
}
