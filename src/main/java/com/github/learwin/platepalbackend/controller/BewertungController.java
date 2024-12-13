package com.github.learwin.platepalbackend.controller;

import com.github.learwin.platepalbackend.PlatePalConstants;
import com.github.learwin.platepalbackend.entity.Bewertung;
import com.github.learwin.platepalbackend.entity.Rezept;
import com.github.learwin.platepalbackend.image.ImageHandler;
import com.github.learwin.platepalbackend.repository.BewertungRepository;
import com.github.learwin.platepalbackend.repository.RezeptRepository;
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

@Controller("/bewertung")
public class BewertungController {
    private final BewertungRepository bewertungRepository;

    BewertungController(BewertungRepository bewertungRepository) {
        this.bewertungRepository = bewertungRepository;
    }

    @Get("/{id}")
    @ExecuteOn(TaskExecutors.BLOCKING)
        // Returns the entity if id is valid
    Optional<Bewertung> getById(long id){
        return bewertungRepository.findById(id);
    }


    @Get("/list")
    @ExecuteOn(TaskExecutors.BLOCKING)
        // Valid url query parameters are for example 'size' and 'page'
    List<Bewertung> list(@Valid Pageable pageable) {
        return bewertungRepository.findAll(pageable).getContent();
    }

    @Post
    @ExecuteOn(TaskExecutors.BLOCKING)
    HttpResponse<Bewertung> createBewertung(@Body @Valid Bewertung bewertung) {
        var createdBewerbung = bewertungRepository.save(bewertung);
        return HttpResponse.status(HttpStatus.CREATED).body(createdBewerbung);
    }

    @Put
    @ExecuteOn(TaskExecutors.BLOCKING)
    HttpResponse<Bewertung> updateBewertung(@Body @Valid Bewertung bewertung) {
        var updatedBewertung = bewertungRepository.update(bewertung);
        return HttpResponse.status(HttpStatus.OK).body(updatedBewertung);
    }

    @Delete("/{id}")
    @ExecuteOn(TaskExecutors.BLOCKING)
    @Status(HttpStatus.NO_CONTENT)
    void deleteBewertung(Long id) {
        bewertungRepository.deleteById(id);
    }

    @Post(value = "/image/{id}", consumes = {MediaType.MULTIPART_FORM_DATA})
    @ExecuteOn(TaskExecutors.BLOCKING)
    HttpResponse<String> uploadBewertungImage(CompletedFileUpload file, @PathVariable long id) {
        return ImageHandler.saveImageForEntity(file, id, bewertungRepository, PlatePalConstants.BEWERTUNG_IMAGE_DIR, "bewertung");
    }

    @Get(value = "/image/{id}", produces = MediaType.APPLICATION_OCTET_STREAM)
    @ExecuteOn(TaskExecutors.BLOCKING)
    public HttpResponse<byte[]> getBewertungImage(long id) {
        return ImageHandler.getImageForEntity(id, bewertungRepository, "bewertung");
    }
}