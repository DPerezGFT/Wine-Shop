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
class RegionController {

    private final RegionRepository repository;

    private final RegionModelAssembler assembler;

    RegionController(RegionRepository repository, RegionModelAssembler assembler){
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/regions")
    CollectionModel<EntityModel<Region>> all(){

        List<EntityModel<Region>> regions = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(regions, linkTo(methodOn(RegionController.class).all()).withSelfRel());
    }

    @GetMapping("/regions/{id}")
    EntityModel<Region> one(@PathVariable Long id) {

        Region region = repository.findById(id) //
                .orElseThrow(() -> new RegionNotFoundException(id));

        return assembler.toModel(region);
    }

    @PostMapping("/regions")
    ResponseEntity<?> newRegion(@RequestBody Region newRegion) {

        EntityModel<Region> entityModel = assembler.toModel(repository.save(newRegion));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @PutMapping("/regions/{id}")
    ResponseEntity<?> replaceRegion(@RequestBody Region newRegion, @PathVariable Long id) {

        Region updatedRegion = repository.findById(id) //
                .map(region -> {
                    region.setName(newRegion.getName());
                    region.setCountry(newRegion.getCountry());
                    return repository.save(region);
                }) //
                .orElseGet(() -> {
                    newRegion.setId(id);
                    return repository.save(newRegion);
                });

        EntityModel<Region> entityModel = assembler.toModel(updatedRegion);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @DeleteMapping("/regions/{id}")
    ResponseEntity<?> deleteRegion(@PathVariable Long id) {

        Region region = repository.findById(id) //
                .orElseThrow(() -> new RegionNotFoundException(id));

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
