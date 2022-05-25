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

@RestController
class WineController {

    private final WineRepository repository;

    private final WineModelAssembler assembler;

    WineController(WineRepository repository, WineModelAssembler assembler){
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/wines")
    CollectionModel<EntityModel<Wine>> all(){

        List<EntityModel<Wine>> wines = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(wines, linkTo(methodOn(WineController.class).all()).withSelfRel());
    }

    @GetMapping("/wines/{id}")
    EntityModel<Wine> one(@PathVariable Long id) {

        Wine wine = repository.findById(id) //
                .orElseThrow(() -> new WineNotFoundException(id));

        return assembler.toModel(wine);
    }
}
