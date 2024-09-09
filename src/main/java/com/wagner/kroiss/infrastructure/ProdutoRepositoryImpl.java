package com.wagner.kroiss.infrastructure;

import com.wagner.kroiss.domain.model.FotoProduto;
import com.wagner.kroiss.domain.repository.ProdutoRepositoryQueries;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @Override
    public FotoProduto save(FotoProduto foto) {
        return manager.merge(foto);
    }


    @Transactional
    @Override
    public void delete(FotoProduto foto) {
        manager.remove(foto);
    }


}
