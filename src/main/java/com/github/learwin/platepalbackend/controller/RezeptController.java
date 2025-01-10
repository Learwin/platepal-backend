package com.github.learwin.platepalbackend.controller;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.github.learwin.platepalbackend.DTO.ZutatRezeptDto;
import com.github.learwin.platepalbackend.PlatePalConstants;
import com.github.learwin.platepalbackend.entity.Rezept;
import com.github.learwin.platepalbackend.entity.Zutat;
import com.github.learwin.platepalbackend.entity.ZutatMengeEinheitAllergen;
import com.github.learwin.platepalbackend.image.ImageHandler;
import com.github.learwin.platepalbackend.repository.RezeptRepository;
import com.github.learwin.platepalbackend.repository.ZutatRezeptRepository;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.multipart.CompletedFileUpload;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.validation.Valid;

import java.util.*;
import java.util.stream.Collectors;

@Controller("/rezepte")
public class RezeptController {
    private final RezeptRepository rezeptRepository;
    private final ZutatRezeptRepository zutatRezeptRepository;

    RezeptController(RezeptRepository rezeptRepository, ZutatRezeptRepository zutatRezeptRepository) {
        this.rezeptRepository = rezeptRepository;
        this.zutatRezeptRepository = zutatRezeptRepository;
    }

    @Get("/{id}")
    @ExecuteOn(TaskExecutors.BLOCKING)
        // Returns the entity if id is valid
    Optional<Rezept> getById(long id){
        return rezeptRepository.findById(id);
    }

    @Get("/full/{id}")
    @ExecuteOn(TaskExecutors.BLOCKING)
        // Returns the entity if id is valid
    Optional<ZutatRezeptDto> getZutatById(long id){
        var rezeptOpt = rezeptRepository.findById(id);
        if (rezeptOpt.isEmpty())
            return Optional.empty();

        var zutatRezept = zutatRezeptRepository.findByRezept_id(rezeptOpt.get());

        var zutatListe = new ArrayList<ZutatMengeEinheitAllergen>();
        for (var zutatRezeptItem : zutatRezept) {
            var zutatdetail = new ZutatMengeEinheitAllergen();
            zutatdetail.setZutat(zutatRezeptItem.getZutat_id());
            zutatdetail.setAllergene(zutatRezeptItem.getZutat_id().getAllergene());
            zutatdetail.setMenge(zutatRezeptItem.getMenge());
            zutatdetail.setEinheit(zutatRezeptItem.getEinheit_id());
            zutatListe.add(zutatdetail);
        }
        ZutatRezeptDto dto = new ZutatRezeptDto(rezeptOpt.get(), zutatListe);

        return Optional.of(dto);
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

    @Post(value = "/image/{id}", consumes = {MediaType.MULTIPART_FORM_DATA})
    @ExecuteOn(TaskExecutors.BLOCKING)
    HttpResponse<String> uploadRezeptImage(CompletedFileUpload file, @PathVariable long id) {
        return ImageHandler.saveImageForEntity(file, id, rezeptRepository, PlatePalConstants.REZEPT_IMAGE_DIR, "rezept");
    }

    @Get(value = "/image/{id}", produces = MediaType.APPLICATION_OCTET_STREAM)
    @ExecuteOn(TaskExecutors.BLOCKING)
    public HttpResponse<byte[]> getRezeptImage(long id) {
        return ImageHandler.getImageForEntity(id, rezeptRepository, "rezept");
    }

    @Get(value = "/byname")
    @ExecuteOn(TaskExecutors.BLOCKING)
    public Page<Rezept> getRezeptByName(@QueryValue String name, @Valid Pageable pageable) {
        return rezeptRepository.findByNameContainingIgnoreCase(name, pageable);
    }
}
