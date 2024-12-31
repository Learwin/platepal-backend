package com.github.learwin.platepalbackend.controller;

import com.github.learwin.platepalbackend.entity.Zutat;
import com.github.learwin.platepalbackend.entity.ZutatDerWoche;
import com.github.learwin.platepalbackend.repository.ZutatDerWocheRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.validation.Valid;

import java.time.LocalDate;
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
        return zutatDerWocheRepository.findByVonLessThanEqualsAndBisGreaterThanEquals(LocalDate.now(), LocalDate.now());
    }

    @Post
    @ExecuteOn(TaskExecutors.BLOCKING)
    HttpResponse<ZutatDerWoche> createZutatDerWoche(@Body @Valid ZutatDerWoche zutat) {
        var createdZDW = zutatDerWocheRepository.save(zutat);
        return HttpResponse.status(HttpStatus.CREATED).body(createdZDW);
    }

    @Put
    @ExecuteOn(TaskExecutors.BLOCKING)
    HttpResponse<ZutatDerWoche> updateZutatDerWoche(@Body @Valid ZutatDerWoche zutat) {
        var updatedZDW = zutatDerWocheRepository.update(zutat);
        return HttpResponse.status(HttpStatus.OK).body(updatedZDW);
    }

    @Delete("/{id}")
    @ExecuteOn(TaskExecutors.BLOCKING)
    @Status(HttpStatus.NO_CONTENT)
    void deleteZutatDerWoche(Long id) {
        zutatDerWocheRepository.deleteById(id);
    }

}
