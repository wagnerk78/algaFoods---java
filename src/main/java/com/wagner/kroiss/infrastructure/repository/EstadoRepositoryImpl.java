package com.wagner.kroiss.infrastructure.repository;

import com.wagner.kroiss.domain.model.Cidade;
import com.wagner.kroiss.domain.model.Estado;
import com.wagner.kroiss.domain.repository.CidadeRepository;
import com.wagner.kroiss.domain.repository.EstadoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EstadoRepositoryImpl implements EstadoRepository {


    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Estado> listar(){
        return manager.createQuery("from Estado", Estado.class).getResultList();
    }

    @Override
    public Estado buscar(Long id){
        return  manager.find(Estado.class, id);
    }

    @Override
    @Transactional
    public void salvar(Estado estado) {
        manager.merge(estado);
    }

    @Override
    @Transactional
    public void remover(Estado estado) {
        estado = buscar(estado.getId());
        manager.remove(estado);
    }
}
