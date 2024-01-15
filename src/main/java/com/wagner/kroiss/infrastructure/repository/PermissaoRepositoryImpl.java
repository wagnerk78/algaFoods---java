package com.wagner.kroiss.infrastructure.repository;

import com.wagner.kroiss.domain.model.Permissao;
import com.wagner.kroiss.domain.model.Restaurante;
import com.wagner.kroiss.domain.repository.PermissaoRepository;
import com.wagner.kroiss.domain.repository.RestauranteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class PermissaoRepositoryImpl implements PermissaoRepository {


    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Permissao> listar(){
        return manager.createQuery("from Permissao", Permissao.class).getResultList();
    }

    @Override
    public Permissao buscar(Long id){
        return  manager.find(Permissao.class, id);
    }

    @Override
    @Transactional
    public void salvar(Permissao permissao) {
        manager.merge(permissao);
    }

    @Override
    @Transactional
    public void remover(Permissao permissao) {
        permissao = buscar(permissao.getId());
        manager.remove(permissao);
    }
}
