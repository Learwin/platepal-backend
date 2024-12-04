package com.github.learwin.platepalbackend.controller;

import com.github.learwin.platepalbackend.entity.ZutatDerWoche;
import com.github.learwin.platepalbackend.repository.ZutatDerWocheRepository;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;

import java.util.List;

@Controller("/zutatderwoche")
public class ZutatDerWocheController {

    private final ZutatDerWocheRepository zutatDerWocheRepository;

    ZutatDerWocheController(ZutatDerWocheRepository zutatDerWocheRepository) {
        this.zutatDerWocheRepository = zutatDerWocheRepository;
    }

    @ExecuteOn(TaskExecutors.BLOCKING)
    @Get
    List<ZutatDerWoche> index() {
        return zutatDerWocheRepository.findAll();
    }
}
