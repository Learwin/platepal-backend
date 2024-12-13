package com.github.learwin.platepalbackend.controller;

import com.github.learwin.platepalbackend.entity.Allergen;
import com.github.learwin.platepalbackend.entity.Timer;
import com.github.learwin.platepalbackend.repository.AllergenRepository;
import com.github.learwin.platepalbackend.repository.TimerRepository;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@Controller("/timer")
public class TimerController {
    private final TimerRepository timerRepository;

    TimerController(TimerRepository timerRepository) {
        this.timerRepository = timerRepository;
    }

    @Get("/{zeit}")
    @ExecuteOn(TaskExecutors.BLOCKING)
        // Returns the entity if id is valid
    Optional<Timer> getById(float zeit){
        return timerRepository.findById(zeit);
    }


    @Get("/list")
    @ExecuteOn(TaskExecutors.BLOCKING)
        // Valid url query parameters are for example 'size' and 'page'
    List<Timer> list(@Valid Pageable pageable) {
        return timerRepository.findAll(pageable).getContent();
    }

    @Post
    @ExecuteOn(TaskExecutors.BLOCKING)
    HttpResponse<Timer> createAllergen(@Body @Valid Timer timer) {
        var createdTimer = timerRepository.save(timer);
        return HttpResponse.status(HttpStatus.CREATED).body(createdTimer);
    }

    @Put
    @ExecuteOn(TaskExecutors.BLOCKING)
    HttpResponse<Timer> updateAllergen(@Body @Valid Timer timer) {
        var updatedTimer = timerRepository.update(timer);
        return HttpResponse.status(HttpStatus.OK).body(updatedTimer);
    }

    @Delete("/{zeit}")
    @ExecuteOn(TaskExecutors.BLOCKING)
    @Status(HttpStatus.NO_CONTENT)
    void deleteZeit(Float zeit) {
        timerRepository.deleteById(zeit);
    }
}
