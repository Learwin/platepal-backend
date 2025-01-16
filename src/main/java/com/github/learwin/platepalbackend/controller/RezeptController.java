package com.github.learwin.platepalbackend.controller;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.github.learwin.platepalbackend.DTO.RezeptVollDTO;
import com.github.learwin.platepalbackend.DTO.ZutatDTO;
import com.github.learwin.platepalbackend.DTO.ZutatRezeptDto;
import com.github.learwin.platepalbackend.PlatePalConstants;
import com.github.learwin.platepalbackend.entity.*;
import com.github.learwin.platepalbackend.entity.ids.ZutatRezeptId;
import com.github.learwin.platepalbackend.image.ImageHandler;
import com.github.learwin.platepalbackend.repository.RezeptRepository;
import com.github.learwin.platepalbackend.repository.ZutatRepository;
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
    private final ZutatRepository zutatRepository;


    RezeptController(RezeptRepository rezeptRepository, ZutatRezeptRepository zutatRezeptRepository, ZutatRepository zutatRepository) {
        this.rezeptRepository = rezeptRepository;
        this.zutatRezeptRepository = zutatRezeptRepository;
        this.zutatRepository = zutatRepository;
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
            zutatdetail.setZutat(zutatRezeptItem.getId().getZutat_id());
            zutatdetail.setAllergene(zutatRezeptItem.getId().getZutat_id().getAllergene());
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
    HttpResponse<Rezept> createRezept(@Body @Valid RezeptVollDTO rezeptDto) {

        var rezept = new Rezept();
        rezept.setAnweisungen(rezeptDto.getAnweisungen());
        rezept.setName(rezeptDto.getName());
        rezept.setFlag(rezeptDto.getFlag());
        rezept.setDefaultPortionen(rezeptDto.getDefaultPortionen());
        rezept.setSchwierigkeit(rezeptDto.getSchwierigkeit());
        rezept.setUser_Id(rezeptDto.getUser());
        rezept.setZeit(rezeptDto.getZeit());

        var createdRezept = rezeptRepository.save(rezept);

        for (var zutatdto : rezeptDto.getZutaten()) {
           var zutatRezept = new ZutatRezept();
           var zutat = new Zutat();
           zutat.setId((long) zutatdto.getZutat());
           zutatRezept.setMenge(zutatdto.getMenge());
           zutatRezept.setId(new ZutatRezeptId(zutat, createdRezept));
           zutatRezept.setEinheit_id(zutatdto.getEinheit());
           zutatRezeptRepository.save(zutatRezept);
        }
        return HttpResponse.status(HttpStatus.CREATED).body(createdRezept);
    }

    @Put
    @ExecuteOn(TaskExecutors.BLOCKING)
    HttpResponse<Rezept> updateRezept(@Body @Valid RezeptVollDTO rezeptDto) {
        Rezept rezept = CheckRezeptEigenschaften(rezeptDto);
        rezept.setId(rezeptDto.getId());
        var updatedRezept = rezeptRepository.update(rezept);
        for (var zutatdto : rezeptDto.getZutaten()) {
            var zutatRezept = CheckZutatRezeptEigenschaften(zutatdto, updatedRezept);
            if (zutatRezeptRepository.findById(zutatRezept.getId()).isPresent())
                zutatRezeptRepository.update(zutatRezept);
            else
                zutatRezeptRepository.save(zutatRezept);
        }
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

    private Rezept CheckRezeptEigenschaften(RezeptVollDTO rezeptDto){
        var rezept = new Rezept();
        if (rezeptDto.getAnweisungen() != null)
        {
            rezept.setAnweisungen(rezeptDto.getAnweisungen());
        }
        if (rezeptDto.getName() != null)
        {
            rezept.setName(rezeptDto.getName());
        }
        if (rezeptDto.getFlag() != null)
        {
            rezept.setFlag(rezeptDto.getFlag());
        }
        if (rezeptDto.getDefaultPortionen() != null)
        {
            rezept.setDefaultPortionen(rezeptDto.getDefaultPortionen());
        }
        if (rezeptDto.getSchwierigkeit() != null)
        {
            rezept.setSchwierigkeit(rezeptDto.getSchwierigkeit());
        }
        if (rezeptDto.getUser() != null)
        {
            rezept.setUser_Id(rezeptDto.getUser());
        }
        if (rezeptDto.getZeit() != null)
        {
            rezept.setZeit(rezeptDto.getZeit());
        }
        return rezept;
    }

    private ZutatRezept CheckZutatRezeptEigenschaften (ZutatDTO zutatDto, Rezept rezept)
    {
//        var zutatRezept = new ZutatRezept();
//        zutatRezept.setRezept_id(createdRezept);
//        var zutat = new Zutat();
//        zutat.setId((long) zutatdto.getZutat());
//        zutatRezept.setMenge(zutatdto.getMenge());
//        zutatRezept.setZutat_id(zutat);
//        zutatRezept.setEinheit_id(zutatdto.getEinheit());
//        zutatRezeptRepository.save(zutatRezept);
        var zutatRezept = new ZutatRezept();
        zutatRezept.setId(new ZutatRezeptId());
        zutatRezept.getId().setRezept_id(rezept);
        var zutat = new Zutat();
        if (zutatDto.getZutat() != null)
        {
            zutatRezept.setId(new ZutatRezeptId(zutatRepository.findById((long)zutatDto.getZutat()).get(),rezept));
            zutat.setId((long) zutatDto.getZutat());
        }
        if (zutatDto.getMenge() != null)
        {
            zutatRezept.setMenge(zutatDto.getMenge());
        }
        zutatRezept.getId().setZutat_id(zutat);
        if (zutatDto.getEinheit() != null)
        {
            zutatRezept.setEinheit_id(zutatDto.getEinheit());
        }
        return zutatRezept;
    }
}
