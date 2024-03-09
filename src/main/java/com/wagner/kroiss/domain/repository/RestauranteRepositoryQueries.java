package com.wagner.kroiss.domain.repository;

import java.math.BigDecimal;
import java.util.List;


import com.wagner.kroiss.domain.model.Restaurante;

public interface RestauranteRepositoryQueries {

    List<Restaurante> find(String nome,
                           BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);

}
