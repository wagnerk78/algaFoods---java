package com.wagner.kroiss.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.wagner.kroiss.api.assembler.CidadeInputDisassembler;
import com.wagner.kroiss.api.assembler.CidadeModelAssembler;
import com.wagner.kroiss.api.model.CidadeModel;
import com.wagner.kroiss.api.model.input.CidadeInput;
import com.wagner.kroiss.domain.exception.EstadoNaoEncontradoException;
import com.wagner.kroiss.domain.exception.NegocioException;
import com.wagner.kroiss.domain.model.Cidade;
import com.wagner.kroiss.domain.repository.CidadeRepository;
import com.wagner.kroiss.domain.service.CadastroCidadeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@Api(tags = "Cidades")
@RestController
@RequestMapping(value = "/cidades")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroCidadeService cadastroCidade;

    @Autowired
    private CidadeModelAssembler cidadeModelAssembler;

    @Autowired
    private CidadeInputDisassembler cidadeInputDisassembler;

    @ApiOperation("Lista todas as cidade")
    @GetMapping
    public List<CidadeModel> listar() {
        List<Cidade> todasCidades = cidadeRepository.findAll();

        return cidadeModelAssembler.toCollectionModel(todasCidades);
    }

    @ApiOperation("Lista uma cidade por Id")
    @GetMapping("/{cidadeId}")
    public CidadeModel buscar(
            @ApiParam(value = "ID de uma cidade", example = "1")
            @PathVariable Long cidadeId) {
        Cidade cidade = cadastroCidade.buscarOuFalhar(cidadeId);

        return cidadeModelAssembler.toModel(cidade);
    }

    @ApiOperation("Adiciona uma cidades")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeModel adicionar(
            @ApiParam(value = "ID de uma cidade", example = "1")
            @RequestBody @Valid CidadeInput cidadeInput) {
        try {
            Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);

            cidade = cadastroCidade.salvar(cidade);

            return cidadeModelAssembler.toModel(cidade);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @ApiOperation("Atualiza uma cidade por Id")
    @PutMapping("/{cidadeId}")
    public CidadeModel atualizar(
            @ApiParam(value = "ID de uma cidade", example = "1")
            @PathVariable Long cidadeId,

            @ApiParam(name = "corpo", value = "Representação de uma cidade com novos dados")
            @RequestBody @Valid CidadeInput cidadeInput) {
        try {
            Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(cidadeId);

            cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidadeAtual);

            cidadeAtual = cadastroCidade.salvar(cidadeAtual);

            return cidadeModelAssembler.toModel(cidadeAtual);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @ApiOperation("Exclui uma cidade por Id")
    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(
            @ApiParam(value = "ID de uma cidade", example = "1")
            @PathVariable Long cidadeId) {
        cadastroCidade.excluir(cidadeId);
    }

}