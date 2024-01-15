package com.wagner.kroiss.infrastructure.repository;

import com.wagner.kroiss.domain.model.Cozinha;
import com.wagner.kroiss.domain.model.Restaurante;
import com.wagner.kroiss.domain.repository.RestauranteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepository {


    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurante> listar(){
        return manager.createQuery("from Restaurante", Restaurante.class).getResultList();
    }

    @Override
    public Restaurante buscar(Long id){
        return  manager.find(Restaurante.class, id);
    }

    @Override
    @Transactional
    public void salvar(Restaurante restaurante) {
        manager.merge(restaurante);
    }

    @Override
    @Transactional
    public void remover(Restaurante restaurante) {
        restaurante = buscar(restaurante.getId());
        manager.remove(restaurante);
    }
}
