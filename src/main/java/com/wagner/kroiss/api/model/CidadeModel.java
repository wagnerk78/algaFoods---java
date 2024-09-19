package com.wagner.kroiss.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CidadeModel {

    @ApiModelProperty(example = "1", required = true)
    private Long id;

    @ApiModelProperty(example = "Rio de Janeiro", required = true)
    private String nome;
    private EstadoModel estado;

}
