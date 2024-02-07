package com.wagner.kroiss.domain.repository;

import com.wagner.kroiss.domain.model.Cozinha;

import java.util.List;


public interface CozinhaRepository {


    List<Cozinha> listar();
    Cozinha buscar(Long id);
    Cozinha salvar(Cozinha cozinha);
    void remover(Long id);

}
