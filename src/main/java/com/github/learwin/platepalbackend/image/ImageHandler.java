package com.github.learwin.platepalbackend.image;

import io.micronaut.data.repository.CrudRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.multipart.CompletedFileUpload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

public class ImageHandler {

    public static <T extends IImage> HttpResponse<String> saveImageForEntity(
            CompletedFileUpload file,
            long entityId,
            CrudRepository<T, Long> entityRepository,
            String imageDirectory,
            String entityName) {
        var fileName = file.getFilename();

        try {
            var targetFile = new File(imageDirectory + '/' + fileName).getCanonicalFile();
            var uploadDir = new File(imageDirectory).getCanonicalFile();

            if (!targetFile.getPath().startsWith(uploadDir.getPath())) {
                return HttpResponse.badRequest("Invalid file path.");
            }

            var parentDir = targetFile.getParentFile();
            if (!parentDir.exists() && !parentDir.mkdirs()) {
                return HttpResponse.serverError("Failed to create directories for file upload.");
            }

            try (var outputStream = new FileOutputStream(targetFile)) {
                Optional<T> entityOpt = entityRepository.findById(entityId);

                if (entityOpt.isEmpty())
                    return HttpResponse.notFound("Entity: " + entityName + " with Id: " + entityId + " not found");

                var entity = entityOpt.get();
                outputStream.write(file.getBytes());
                entity.setFoto(imageDirectory + '/' + fileName);
                entityRepository.update(entity);
                return HttpResponse.ok("File uploaded successfully: " + fileName);
            }
        } catch (IOException ioException) {
            return HttpResponse.serverError("Failed to save file: " + fileName + " to entity: " + entityName + " with ID: " + entityId);
        }
    }

    public static <T extends IImage> HttpResponse<byte[]> getImageForEntity(
            long entityId,
            CrudRepository<T, Long> entityRepository,
            String entityName) {

        Optional<T> entityOpt = entityRepository.findById(entityId);
        if (entityOpt.isEmpty())
            return HttpResponse.notFound();

        var entity = entityOpt.get();
        var fileName = entity.getFoto();

        if (fileName == null)
            return HttpResponse.notFound(("No image for Entity: " + entityName + " with id: " + entityId).getBytes());
        try {
            var file = new File(fileName).getCanonicalFile();

            if (!file.exists()) {
                return HttpResponse.notFound();
            }

            var fileBytes = Files.readAllBytes(file.toPath());
            return HttpResponse.ok(fileBytes).header("Content-Disposition", "attachment; filename=" + fileName);

        } catch (IOException e) {
            return HttpResponse.serverError(("Failed to read file: " + fileName + " from entity: " + entityName + " with ID: " + entityId).getBytes());
        }
    }

}
