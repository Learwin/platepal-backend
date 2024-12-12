package com.github.learwin.platepalbackend.controller;

import com.github.learwin.platepalbackend.entity.Allergen;
import com.github.learwin.platepalbackend.repository.AllergenRepository;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@Controller("/allergen")
public class AllergenController {
    private final AllergenRepository allergenRepository;

    AllergenController(AllergenRepository allergenRepository) {
        this.allergenRepository = allergenRepository;
    }

    @Get("/{id}")
    @ExecuteOn(TaskExecutors.BLOCKING)
        // Returns the entity if id is valid
    Optional<Allergen> getById(long id){
        return allergenRepository.findById(id);
    }


    @Get("/list")
    @ExecuteOn(TaskExecutors.BLOCKING)
        // Valid url query parameters are for example 'size' and 'page'
    List<Allergen> list(@Valid Pageable pageable) {
        return allergenRepository.findAll(pageable).getContent();
    }

    @Post
    @ExecuteOn(TaskExecutors.BLOCKING)
    HttpResponse<Allergen> createAllergen(@Body @Valid Allergen allergen) {
        var createdAllergen = allergenRepository.save(allergen);
        return HttpResponse.status(HttpStatus.CREATED).body(createdAllergen);
    }

    @Put
    @ExecuteOn(TaskExecutors.BLOCKING)
    HttpResponse<Allergen> updateAllergen(@Body @Valid Allergen allergen) {
        var updatedAllergen = allergenRepository.update(allergen);
        return HttpResponse.status(HttpStatus.OK).body(updatedAllergen);
    }

    @Delete("/{id}")
    @ExecuteOn(TaskExecutors.BLOCKING)
    @Status(HttpStatus.NO_CONTENT)
    void deleteAllergen(Long id) {
        allergenRepository.deleteById(id);
    }
}
