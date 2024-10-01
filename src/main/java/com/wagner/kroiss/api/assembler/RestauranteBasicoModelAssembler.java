package com.wagner.kroiss.api.assembler;

import com.wagner.kroiss.api.AlgaLinks;
import com.wagner.kroiss.api.controller.RestauranteController;
import com.wagner.kroiss.api.model.RestauranteBasicoModel;
import com.wagner.kroiss.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class RestauranteBasicoModelAssembler
        extends RepresentationModelAssemblerSupport<Restaurante, RestauranteBasicoModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    public RestauranteBasicoModelAssembler() {
        super(RestauranteController.class, RestauranteBasicoModel.class);
    }

    @Override
    public RestauranteBasicoModel toModel(Restaurante restaurante) {
        RestauranteBasicoModel restauranteModel = createModelWithId(
                restaurante.getId(), restaurante);

        modelMapper.map(restaurante, restauranteModel);

        restauranteModel.add(algaLinks.linkToRestaurantes("restaurantes"));

        restauranteModel.getCozinha().add(
                algaLinks.linkToCozinha(restaurante.getCozinha().getId()));

        return restauranteModel;
    }

    @Override
    public CollectionModel<RestauranteBasicoModel> toCollectionModel(Iterable<? extends Restaurante> entities) {
        return super.toCollectionModel(entities)
                .add(algaLinks.linkToRestaurantes());
    }
}
