package com.github.learwin.platepalbackend.controller;

import com.github.learwin.platepalbackend.PlatePalConstants;
import com.github.learwin.platepalbackend.entity.Zutat;
import com.github.learwin.platepalbackend.image.ImageHandler;
import com.github.learwin.platepalbackend.repository.ZutatRepository;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.multipart.CompletedFileUpload;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@Controller("/zutat")
public class ZutatController {

    private final ZutatRepository zutatRepository;

    ZutatController(ZutatRepository zutatRepository) {
        this.zutatRepository = zutatRepository;
    }

    @Get("/{id}")
    @ExecuteOn(TaskExecutors.BLOCKING)
        // Returns the entity if id is valid
    Optional<Zutat> getById(long id) {
        return zutatRepository.findById(id);
    }

    @Get("/list")
    @ExecuteOn(TaskExecutors.BLOCKING)
        // Valid url query parameters are for example 'size' and 'page'
    List<Zutat> list(@Valid Pageable pageable) {
        return zutatRepository.findAll(pageable).getContent();
    }

    @Post
    @ExecuteOn(TaskExecutors.BLOCKING)
    HttpResponse<Zutat> createZutat(@Body @Valid Zutat zutat) {
        var createdZutat = zutatRepository.save(zutat);
        return HttpResponse.status(HttpStatus.CREATED).body(createdZutat);
    }

    @Put
    @ExecuteOn(TaskExecutors.BLOCKING)
    HttpResponse<Zutat> updateZutat(@Body @Valid Zutat zutat) {
        var updatedZutat = zutatRepository.update(zutat);
        return HttpResponse.status(HttpStatus.OK).body(updatedZutat);
    }

    @Delete("/{id}")
    @ExecuteOn(TaskExecutors.BLOCKING)
    @Status(HttpStatus.NO_CONTENT)
    void deleteZutat(Long id) {
        zutatRepository.deleteById(id);
    }

    @Post(value = "/image/{id}", consumes = {MediaType.MULTIPART_FORM_DATA})
    @ExecuteOn(TaskExecutors.BLOCKING)
    HttpResponse<String> uploadZutatImage(CompletedFileUpload file, @PathVariable long id) {
        return ImageHandler.saveImageForEntity(file, id, zutatRepository, PlatePalConstants.ZUTAT_IMAGE_DIR, "zutat");
    }

    @Get(value = "/image/{id}", produces = MediaType.APPLICATION_OCTET_STREAM)
    @ExecuteOn(TaskExecutors.BLOCKING)
    public HttpResponse<byte[]> getZutatImage(long id) {
        return ImageHandler.getImageForEntity(id, zutatRepository, "zutat");
    }
}
