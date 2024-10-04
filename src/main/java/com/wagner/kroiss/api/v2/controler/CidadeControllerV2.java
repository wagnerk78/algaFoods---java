package com.wagner.kroiss.api.v2.controler;

import com.wagner.kroiss.api.v1.assembler.CidadeInputDisassembler;
import com.wagner.kroiss.api.v1.assembler.CidadeModelAssembler;
import com.wagner.kroiss.api.v1.model.CidadeModel;
import com.wagner.kroiss.api.v1.model.input.CidadeInput;
import com.wagner.kroiss.api.v1.openApi.controller.CidadeControllerOpenApi;
import com.wagner.kroiss.api.v2.assembler.CidadeInputDisassemblerV2;
import com.wagner.kroiss.api.v2.assembler.CidadeModelAssemblerV2;
import com.wagner.kroiss.api.v2.model.CidadeModelV2;
import com.wagner.kroiss.api.v2.model.input.CidadeInputV2;
import com.wagner.kroiss.core.web.AlgaMediaTypes;
import com.wagner.kroiss.domain.exception.EstadoNaoEncontradoException;
import com.wagner.kroiss.domain.exception.NegocioException;
import com.wagner.kroiss.domain.model.Cidade;
import com.wagner.kroiss.domain.repository.CidadeRepository;
import com.wagner.kroiss.domain.service.CadastroCidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(path = "/cidades")
public class CidadeControllerV2  {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroCidadeService cadastroCidade;

    @Autowired
    private CidadeModelAssemblerV2 cidadeModelAssembler;

    @Autowired
    private CidadeInputDisassemblerV2 cidadeInputDisassembler;


    @GetMapping(produces = AlgaMediaTypes.V2_APPLICATION_JSON_VALUE)
    public CollectionModel<CidadeModelV2> listar() {
        List<Cidade> todasCidades = cidadeRepository.findAll();

        return cidadeModelAssembler.toCollectionModel(todasCidades);
    }


    @GetMapping(path = "/{cidadeId}", produces = AlgaMediaTypes.V2_APPLICATION_JSON_VALUE)
    public CidadeModelV2 buscar(@PathVariable Long cidadeId) {
        Cidade cidade = cadastroCidade.buscarOuFalhar(cidadeId);

        return cidadeModelAssembler.toModel(cidade);
    }

    @PostMapping(produces = AlgaMediaTypes.V2_APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeModelV2 adicionar(@RequestBody @Valid CidadeInputV2 cidadeInput) {
        try {
            Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);

            cidade = cadastroCidade.salvar(cidade);

            return cidadeModelAssembler.toModel(cidade);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }


    @PutMapping(path = "/{cidadeId}", produces = AlgaMediaTypes.V2_APPLICATION_JSON_VALUE)
    public CidadeModelV2 atualizar(@PathVariable Long cidadeId,
                                 @RequestBody @Valid CidadeInputV2 cidadeInput) {
        try {
            Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(cidadeId);

            cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidadeAtual);

            cidadeAtual = cadastroCidade.salvar(cidadeAtual);

            return cidadeModelAssembler.toModel(cidadeAtual);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }


//    @DeleteMapping("/{cidadeId}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void remover(@PathVariable Long cidadeId) {
//        cadastroCidade.excluir(cidadeId);
//    }

}