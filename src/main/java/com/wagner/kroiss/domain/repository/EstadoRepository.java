package com.wagner.kroiss.domain.repository;

import com.wagner.kroiss.domain.model.Estado;

import java.util.List;


public interface EstadoRepository {


    List<Estado> listar();
    Estado buscar(Long id);
    Estado salvar(Estado estado);
    void remover(Long id);

}
