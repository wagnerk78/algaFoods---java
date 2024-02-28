package com.wagner.kroiss.domain.api.controller;


import com.wagner.kroiss.domain.exceptions.EntidadeEmUsoException;
import com.wagner.kroiss.domain.exceptions.EntidadeNaoEncontrada;
import com.wagner.kroiss.domain.model.Cidade;
import com.wagner.kroiss.domain.model.Cozinha;
import com.wagner.kroiss.domain.repository.CidadeRepository;
import com.wagner.kroiss.domain.service.CadastroCidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;


    @Autowired
    private CadastroCidadeService cadastroCidadeService;

    @GetMapping
    public List<Cidade> listar() {
        return cidadeRepository.listar();
    }

    @GetMapping("/{cidadeId}")
    public ResponseEntity<Cidade> buscar(@PathVariable("cidadeId") Long id) {
        Cidade cidade = cidadeRepository.buscar(id);

        if (cidade != null) {
            return ResponseEntity.ok(cidade);
        }

        return ResponseEntity.notFound().build();
    }


    @PostMapping
    public  void adicionar(@RequestBody Cidade cidade) {
        cadastroCidadeService.salvar(cidade);
    }


    @PutMapping("/{cidadeId}")
    public ResponseEntity<Cidade> atualizar(@PathVariable Long cidadeId, @RequestBody Cidade cidade){
        Cidade cidadeAtual = cidadeRepository.buscar(cidadeId);

        if (cidadeAtual != null) {
            BeanUtils.copyProperties(cidade, cidadeAtual, "id");

            cidadeRepository.salvar(cidadeAtual);

            return ResponseEntity.ok(cidadeAtual);
        }

        return ResponseEntity.notFound().build();


    }


    @DeleteMapping("/{cidadeId}")
    public ResponseEntity<Cidade> remover(@PathVariable Long cidadeId) {
        try {
            cadastroCidadeService.excluir(cidadeId);

                return ResponseEntity.ok().build();


//            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (EntidadeNaoEncontrada e) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }




    }



}
