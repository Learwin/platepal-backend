package com.github.learwin.platepalbackend.controller;

import com.github.learwin.platepalbackend.entity.Einheit;
import com.github.learwin.platepalbackend.repository.EinheitRepository;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@Controller("/einheit")
public class EinheitController {
    private final EinheitRepository einheitRepository;

    EinheitController(EinheitRepository einheitRepository) {
        this.einheitRepository = einheitRepository;
    }

    @Get("/{id}")
    @ExecuteOn(TaskExecutors.BLOCKING)
        // Returns the entity if id is valid
    Optional<Einheit> getById(long id){
        return einheitRepository.findById(id);
    }


    @Get("/list")
    @ExecuteOn(TaskExecutors.BLOCKING)
        // Valid url query parameters are for example 'size' and 'page'
    List<Einheit> list(@Valid Pageable pageable) {
        return einheitRepository.findAll(pageable).getContent();
    }

    @Post
    @ExecuteOn(TaskExecutors.BLOCKING)
    HttpResponse<Einheit> createEinheit(@Body @Valid Einheit einheit) {
        var createdEinheit = einheitRepository.save(einheit);
        return HttpResponse.status(HttpStatus.CREATED).body(createdEinheit);
    }

    @Put
    @ExecuteOn(TaskExecutors.BLOCKING)
    HttpResponse<Einheit> updateEinheit(@Body @Valid Einheit einheit) {
        var updatedEinheit = einheitRepository.update(einheit);
        return HttpResponse.status(HttpStatus.OK).body(updatedEinheit);
    }

    @Delete("/{id}")
    @ExecuteOn(TaskExecutors.BLOCKING)
    @Status(HttpStatus.NO_CONTENT)
    void deleteEinheit(Long id) {
        einheitRepository.deleteById(id);
    }
}
