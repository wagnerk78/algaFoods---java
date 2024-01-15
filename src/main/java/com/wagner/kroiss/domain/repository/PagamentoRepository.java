package com.wagner.kroiss.domain.repository;

import com.wagner.kroiss.domain.model.Cozinha;
import com.wagner.kroiss.domain.model.Pagamento;

import java.util.List;


public interface PagamentoRepository {


    List<Pagamento> listar();
    Pagamento buscar(Long id);
    void salvar(Pagamento pagamento);
    void remover(Pagamento pagamento);

}
