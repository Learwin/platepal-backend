package com.github.learwin.platepalbackend.controller;

import com.github.learwin.platepalbackend.PlatePalConstants;
import com.github.learwin.platepalbackend.entity.User;
import com.github.learwin.platepalbackend.image.ImageHandler;
import com.github.learwin.platepalbackend.repository.UserRepository;
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

@Controller("/user")
public class UserController {
    private final UserRepository userRepository;

    UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Get("/{id}")
    @ExecuteOn(TaskExecutors.BLOCKING)
        // Returns the entity if id is valid
    Optional<User> getById(long id){
        return userRepository.findById(id);
    }


    @Get("/list")
    @ExecuteOn(TaskExecutors.BLOCKING)
        // Valid url query parameters are for example 'size' and 'page'
    List<User> list(@Valid Pageable pageable) {
        return userRepository.findAll(pageable).getContent();
    }

    @Post
    @ExecuteOn(TaskExecutors.BLOCKING)
    HttpResponse<User> createUser(@Body @Valid User user) {
        var createdUser = userRepository.save(user);
        return HttpResponse.status(HttpStatus.CREATED).body(createdUser);
    }

    @Put
    @ExecuteOn(TaskExecutors.BLOCKING)
    HttpResponse<User> updateUser(@Body @Valid User user) {
        var updatedUser = userRepository.update(user);
        return HttpResponse.status(HttpStatus.OK).body(updatedUser);
    }

    @Delete("/{id}")
    @ExecuteOn(TaskExecutors.BLOCKING)
    @Status(HttpStatus.NO_CONTENT)
    void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Get("/get/withmail")
    @ExecuteOn(TaskExecutors.BLOCKING)
    Optional<User> getUserByID(@QueryValue String mail){
        return userRepository.getByemailAdresse(mail);
    }

    @Post(value = "/image/{id}", consumes = {MediaType.MULTIPART_FORM_DATA})
    @ExecuteOn(TaskExecutors.BLOCKING)
    HttpResponse<String> uploadUserImage(CompletedFileUpload file, @PathVariable long id) {
        return ImageHandler.saveImageForEntity(file, id, userRepository, PlatePalConstants.USER_IMAGE_DIR, "usertable");
    }

    @Get(value = "/image/{id}", produces = MediaType.APPLICATION_OCTET_STREAM)
    @ExecuteOn(TaskExecutors.BLOCKING)
    public HttpResponse<byte[]> getUserImage(long id) {
        return ImageHandler.getImageForEntity(id, userRepository, "usertable");
    }



}

