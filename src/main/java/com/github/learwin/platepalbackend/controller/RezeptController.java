package com.github.learwin.platepalbackend.controller;

import com.github.learwin.platepalbackend.entity.Rezept;
import com.github.learwin.platepalbackend.repository.RezeptRepository;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@Controller("/rezepte")
public class RezeptController {
    private final RezeptRepository rezeptRepository;

    RezeptController(RezeptRepository rezeptRepository) {
        this.rezeptRepository = rezeptRepository;
    }

    @Get("/{id}")
    @ExecuteOn(TaskExecutors.BLOCKING)
        // Returns the entity if id is valid
    Optional<Rezept> getById(long id){
        return rezeptRepository.findById(id);
    }


    @Get("/list")
    @ExecuteOn(TaskExecutors.BLOCKING)
        // Valid url query parameters are for example 'size' and 'page'
    List<Rezept> list(@Valid Pageable pageable) {
        return rezeptRepository.findAll(pageable).getContent();
    }

    @Post
    @ExecuteOn(TaskExecutors.BLOCKING)
    HttpResponse<Rezept> createRezept(@Body @Valid Rezept rezept) {
        var createdRezept = rezeptRepository.save(rezept);
        return HttpResponse.status(HttpStatus.CREATED).body(createdRezept);
    }

    @Put
    @ExecuteOn(TaskExecutors.BLOCKING)
    HttpResponse<Rezept> updateRezept(@Body @Valid Rezept rezept) {
        var updatedRezept = rezeptRepository.update(rezept);
        return HttpResponse.status(HttpStatus.OK).body(updatedRezept);
    }

    @Delete("/{id}")
    @ExecuteOn(TaskExecutors.BLOCKING)
    @Status(HttpStatus.NO_CONTENT)
    void deleteRezept(Long id) {
        rezeptRepository.deleteById(id);
    }
}
