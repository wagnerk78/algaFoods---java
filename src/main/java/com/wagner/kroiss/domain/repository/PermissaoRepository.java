package com.wagner.kroiss.domain.repository;

import com.wagner.kroiss.domain.model.Cozinha;
import com.wagner.kroiss.domain.model.Permissao;

import java.util.List;


public interface PermissaoRepository {


    List<Permissao> listar();
    Permissao buscar(Long id);
    void salvar(Permissao permissao);
    void remover(Permissao permissao);

}
