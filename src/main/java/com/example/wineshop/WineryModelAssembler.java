package com.example.wineshop;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class WineryModelAssembler implements RepresentationModelAssembler<Winery, EntityModel<Winery>> {

    @Override
    public EntityModel<Winery> toModel(Winery winery) {

        return EntityModel.of(winery, //
                linkTo(methodOn(WineryController.class).one(winery.getId())).withSelfRel(),
                linkTo(methodOn(WineryController.class).all()).withRel("Winery List"));
    }
}