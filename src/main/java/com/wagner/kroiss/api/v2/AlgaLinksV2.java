package com.wagner.kroiss.api.v2;


import com.wagner.kroiss.api.v2.controler.CidadeControllerV2;
import com.wagner.kroiss.api.v2.controler.CozinhaControllerV2;
import org.springframework.hateoas.*;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AlgaLinksV2 {

    public Link linkToCidades(String rel) {
        return linkTo(CidadeControllerV2.class).withRel(rel);
    }

    public Link linkToCidades() {
        return linkToCidades(IanaLinkRelations.SELF.value());
    }

    public Link linkToCozinhas(String rel) {
        return linkTo(CozinhaControllerV2.class).withRel(rel);
    }

    public Link linkToCozinhas() {
        return linkToCozinhas(IanaLinkRelations.SELF.value());
    }

}