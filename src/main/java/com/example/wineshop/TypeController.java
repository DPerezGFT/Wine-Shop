package com.example.wineshop;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

class TypeController {

    private final TypeRepository repository;

    private final TypeModelAssembler assembler;

    TypeController(TypeRepository repository, TypeModelAssembler assembler){
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/types")
    CollectionModel<EntityModel<Type>> all(){

        List<EntityModel<Type>> types = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(types, linkTo(methodOn(TypeController.class).all()).withSelfRel());
    }

    @GetMapping("/types/{id}")
    EntityModel<Type> one(@PathVariable Long id) {

        Type type = repository.findById(id) //
                .orElseThrow(() -> new TypeNotFoundException(id));

        return assembler.toModel(type);
    }
}
