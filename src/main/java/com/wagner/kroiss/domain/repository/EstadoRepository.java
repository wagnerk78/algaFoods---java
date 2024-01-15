package com.wagner.kroiss.domain.repository;

import com.wagner.kroiss.domain.model.Cozinha;
import com.wagner.kroiss.domain.model.Estado;

import java.util.List;


public interface EstadoRepository {


    List<Estado> listar();
    Estado buscar(Long id);
    void salvar(Estado estado);
    void remover(Estado estado);

}
