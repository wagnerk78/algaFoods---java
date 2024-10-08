package com.wagner.kroiss.api.v2.model;

import com.wagner.kroiss.api.v1.model.EstadoModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@ApiModel("CidadeModel")
@Relation(collectionRelation = "cidades")
@Setter
@Getter
public class CidadeModelV2 extends RepresentationModel<CidadeModelV2> {

    @ApiModelProperty(example = "1", required = true)
    private Long idCidade;

    @ApiModelProperty(example = "Rio de Janeiro", required = true)
    private String nomeCidade;

    private Long idEstado;

    private String nomeEstado;



}
