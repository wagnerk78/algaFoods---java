package com.wagner.kroiss.api.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Setter
@Getter
public class EstadoModel extends RepresentationModel<EstadoModel> {

    private Long id;
    private String nome;

}