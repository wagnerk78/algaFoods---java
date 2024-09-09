package com.wagner.kroiss.domain.repository;

import com.wagner.kroiss.domain.model.FotoProduto;

public interface ProdutoRepositoryQueries {

    FotoProduto save(FotoProduto foto);

    void delete(FotoProduto foto);

}
