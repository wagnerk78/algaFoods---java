package com.wagner.kroiss.api.v1.openApi.controller;

import com.wagner.kroiss.api.v1.controller.EstatisticasController;
import com.wagner.kroiss.domain.filter.VendaDiariaFilter;
import com.wagner.kroiss.domain.model.dto.VendaDiaria;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Api(tags = "Estatísticas")
public interface EstatisticasControllerOpenApi {

    @ApiOperation("Consulta estatísticas de vendas diárias")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "restauranteId", value = "ID do restaurante",
                    example = "1", dataType = "int"),
            @ApiImplicitParam(name = "dataCriacaoInicio", value = "Data/hora inicial da criação do pedido",
                    example = "2019-12-01T00:00:00Z", dataType = "date-time"),
            @ApiImplicitParam(name = "dataCriacaoFim", value = "Data/hora final da criação do pedido",
                    example = "2019-12-02T23:59:59Z", dataType = "date-time")
    })
    List<VendaDiaria> consultarVendasDiarias(
            VendaDiariaFilter filtro,

            @ApiParam(value = "Deslocamento de horário a ser considerado na consulta em relação ao UTC",
                    defaultValue = "+00:00")
            String timeOffset);

    ResponseEntity<byte[]> consultarVendasDiariasPdf(
            VendaDiariaFilter filtro,
            String timeOffset);

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    EstatisticasController.EstatisticasModel estatisticas();
}
