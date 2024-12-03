package com.github.learwin.platepalbackend.controller;

import com.github.learwin.platepalbackend.entity.Zutat;
import com.github.learwin.platepalbackend.repository.ZutatRepository;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;

import java.util.List;

@Controller("/zutat")
public class ZutatController {

    private final ZutatRepository zutatRepository;

    ZutatController(ZutatRepository zutatRepository) {
        this.zutatRepository = zutatRepository;
    }

    @ExecuteOn(TaskExecutors.BLOCKING)
    @Get
    List<Zutat> index() {
        return zutatRepository.findAll();
    }
}
