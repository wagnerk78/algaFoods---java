package com.wagner.kroiss.domain.service;

import com.wagner.kroiss.domain.filter.VendaDiariaFilter;
import com.wagner.kroiss.domain.model.dto.VendaDiaria;

import java.util.List;

public interface VendaQueryService {

    List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro);

}
