package com.wagner.kroiss.domain.repository;

import com.wagner.kroiss.domain.model.Cozinha;

import java.util.List;


public interface CozinhaRepository {


    List<Cozinha> listar();
    Cozinha buscar(Long id);
    void salvar(Cozinha cozinha);
    void remover(Cozinha cozinha);

}
