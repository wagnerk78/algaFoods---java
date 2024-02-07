package com.wagner.kroiss.infrastructure.repository;

import com.wagner.kroiss.domain.model.Cozinha;
import com.wagner.kroiss.domain.repository.CozinhaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CozinhaRepositoryImpl implements CozinhaRepository {


    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Cozinha> listar(){
        return manager.createQuery("from Cozinha", Cozinha.class).getResultList();
    }

    @Override
    public Cozinha buscar(Long id){
        return  manager.find(Cozinha.class, id);
    }

    @Override
    @Transactional
    public Cozinha salvar(Cozinha cozinha) {
        manager.merge(cozinha);
        return cozinha;
    }

    @Override
    @Transactional
    public void remover(Long id) {
        Cozinha cozinha = buscar(id);

        if (cozinha == null) {
            throw new EmptyResultDataAccessException(1);

        }
        manager.remove(cozinha);
    }
}
