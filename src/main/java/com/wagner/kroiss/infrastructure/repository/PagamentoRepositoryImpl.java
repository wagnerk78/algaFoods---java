package com.wagner.kroiss.infrastructure.repository;

import com.wagner.kroiss.domain.model.Cozinha;
import com.wagner.kroiss.domain.model.Pagamento;
import com.wagner.kroiss.domain.repository.CozinhaRepository;
import com.wagner.kroiss.domain.repository.PagamentoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class PagamentoRepositoryImpl implements PagamentoRepository {


    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Pagamento> listar(){
        return manager.createQuery("from Pagamento", Pagamento.class).getResultList();
    }

    @Override
    public Pagamento buscar(Long id){
        return  manager.find(Pagamento.class, id);
    }

    @Override
    @Transactional
    public void salvar(Pagamento pagamento) {
        manager.merge(pagamento);
    }

    @Override
    @Transactional
    public void remover(Pagamento pagamento) {
        pagamento = buscar(pagamento.getId());
        manager.remove(pagamento);
    }
}
