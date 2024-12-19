package com.github.learwin.platepalbackend.controller;

import com.github.learwin.platepalbackend.entity.ZutatDerWoche;
import com.github.learwin.platepalbackend.repository.ZutatDerWocheRepository;
import io.micronaut.data.annotation.Query;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;

import java.util.List;
import java.util.Optional;

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

    @ExecuteOn(TaskExecutors.BLOCKING)
    @Get(value = "/current")
    Optional<ZutatDerWoche> getCurrentZDW() {
        return zutatDerWocheRepository.getCurrentZDW();
    }

}
