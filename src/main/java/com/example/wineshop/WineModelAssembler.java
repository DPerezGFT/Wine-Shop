package com.example.wineshop;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

/*@Component
class WineModelAssembler implements RepresentationModelAssembler<Wine, EntityModel<Wine>> {

    @Override
    public EntityModel<Wine> toModel(Wine wine) {

        return EntityModel.of(wine, //
                linkTo(methodOn(WineController.class).one(wine.getId())).withSelfRel(),
                linkTo(methodOn(WineController.class).all()).withRel("Wine List"));
    }
}*/