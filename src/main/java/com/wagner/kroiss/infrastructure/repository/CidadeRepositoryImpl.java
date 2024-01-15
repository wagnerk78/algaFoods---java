package com.wagner.kroiss.infrastructure.repository;

import com.wagner.kroiss.domain.model.Cidade;
import com.wagner.kroiss.domain.model.Cozinha;
import com.wagner.kroiss.domain.repository.CidadeRepository;
import com.wagner.kroiss.domain.repository.CozinhaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CidadeRepositoryImpl implements CidadeRepository {


    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Cidade> listar(){
        return manager.createQuery("from Cidade", Cidade.class).getResultList();
    }

    @Override
    public Cidade buscar(Long id){
        return  manager.find(Cidade.class, id);
    }

    @Override
    @Transactional
    public void salvar(Cidade cidade) {
        manager.merge(cidade);
    }

    @Override
    @Transactional
    public void remover(Cidade cidade) {
        cidade = buscar(cidade.getId());
        manager.remove(cidade);
    }
}
