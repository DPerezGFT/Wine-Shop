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

    @PostMapping("/wines")
    ResponseEntity<?> newWine(@RequestBody Wine newWine) {

        EntityModel<Wine> entityModel = assembler.toModel(repository.save(newWine));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @PutMapping("/wines/{id}")
    ResponseEntity<?> replaceWine(@RequestBody Wine newWine, @PathVariable Long id) {

        Wine updatedWine = repository.findById(id) //
                .map(wine -> {
                    wine.setName(newWine.getName());
                    wine.setAcidity(newWine.getAcidity());
                    wine.setBody(newWine.getBody());
                    wine.setNum_reviews(newWine.getNum_reviews());
                    wine.setPrice(newWine.getPrice());
                    wine.setRating(newWine.getRating());
                    wine.setRegion(newWine.getRegion());
                    wine.setType(newWine.getType());
                    wine.setWinery(newWine.getWinery());
                    wine.setYear(newWine.getYear());
                    return repository.save(wine);
                }) //
                .orElseGet(() -> {
                    newWine.setId(id);
                    return repository.save(newWine);
                });

        EntityModel<Wine> entityModel = assembler.toModel(updatedWine);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @DeleteMapping("/wines/{id}")
    ResponseEntity<?> deleteWine(@PathVariable Long id) {

        Wine wine = repository.findById(id) //
                .orElseThrow(() -> new WineNotFoundException(id));

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
