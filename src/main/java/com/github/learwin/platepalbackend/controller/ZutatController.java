package com.github.learwin.platepalbackend.controller;

import com.github.learwin.platepalbackend.PlatePalConstants;
import com.github.learwin.platepalbackend.entity.Zutat;
import com.github.learwin.platepalbackend.repository.ZutatRepository;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.multipart.CompletedFileUpload;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

@Controller("/zutat")
@Tag(name = "Zutat API", description = "Operations for Zutaten")
public class ZutatController {

    private final ZutatRepository zutatRepository;

    ZutatController(ZutatRepository zutatRepository) {
        this.zutatRepository = zutatRepository;
    }

    @Get("/{id}")
    @Operation(summary = "Get a Zutat by ID", description = "Returns a single Zutat")
    @ApiResponse(responseCode = "200", description = "Found Zutat",
    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Zutat.class)))
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
        var filename = file.getFilename();

        try {
            var targetFile = new File(PlatePalConstants.ZUTAT_IMAGE_DIR + '/' + filename).getCanonicalFile();

            File uploadDir = new File(PlatePalConstants.ZUTAT_IMAGE_DIR).getCanonicalFile();
            if (!targetFile.getPath().startsWith(uploadDir.getPath())) {
                return HttpResponse.badRequest("Invalid file path.");
            }

            File parentDir = targetFile.getParentFile();
            if (!parentDir.exists() && !parentDir.mkdirs()) {
                return HttpResponse.serverError("Failed to create directories for file upload.");
            }

            try (FileOutputStream outputStream = new FileOutputStream(targetFile)) {

                Optional<Zutat> zutatOpt = zutatRepository.findById(id);
                if (zutatOpt.isEmpty())
                    return HttpResponse.notFound("Zutat with Id: " + id + " not found");

                var zutat = zutatOpt.get();

                outputStream.write(file.getBytes());

                zutat.setFoto(PlatePalConstants.ZUTAT_IMAGE_DIR + '/' + filename);
                zutatRepository.update(zutat);

                return HttpResponse.ok("File uploaded successfully: " + filename);
            }
        } catch (IOException ioException) {
            return HttpResponse.serverError("Failed to save file: " + filename);
        }
    }

    @Get(value = "/image/{id}", produces = MediaType.APPLICATION_OCTET_STREAM)
    @ExecuteOn(TaskExecutors.BLOCKING)
    public HttpResponse<byte[]> getZutatImage(long id) {

        Optional<Zutat> zutatOpt = zutatRepository.findById(id);
        if (zutatOpt.isEmpty())
            return HttpResponse.notFound();

        var zutat = zutatOpt.get();
        var fileName = zutat.getFoto();

        try {
            File file = new File(fileName).getCanonicalFile();

            if (!file.exists()) {
                return HttpResponse.notFound();
            }

            byte[] fileBytes = Files.readAllBytes(file.toPath());
            return HttpResponse.ok(fileBytes).header("Content-Disposition", "attachment; filename=" + fileName);

        } catch (IOException e) {
            return HttpResponse.serverError();
        }
    }
}
