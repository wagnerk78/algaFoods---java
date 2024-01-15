package com.wagner.kroiss.domain.repository;

import com.wagner.kroiss.domain.model.Cidade;
import com.wagner.kroiss.domain.model.Cozinha;

import java.util.List;


public interface CidadeRepository {


    List<Cidade> listar();
    Cidade buscar(Long id);
    void salvar(Cidade cidade);
    void remover(Cidade cidade);

}
