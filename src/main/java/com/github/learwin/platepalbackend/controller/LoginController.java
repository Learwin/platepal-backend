package com.github.learwin.platepalbackend.controller;

import com.github.learwin.platepalbackend.repository.UserRepository;
import com.google.gson.*;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;

@Controller("/login")
public class LoginController {

    private final UserRepository userRepository;

    LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Post(consumes = {MediaType.APPLICATION_JSON})
    HttpResponse<String> tryLogin(HttpRequest<String> request) {
        var body = request.getBody();
        if (body.isEmpty())
            return HttpResponse.badRequest().body("Body is missing!");

        JsonObject json = JsonParser.parseString(body.get()).getAsJsonObject();
        var mail = json.get("mail").getAsString();
        var password = json.get("password").getAsString();

        var userOpt = userRepository.getByemailAdresse(mail);
        if (userOpt.isEmpty())
            return HttpResponse.notFound("No user with mail: " + mail + " found!");

        var user = userOpt.get();
        if (password.equals(user.getPasswort()))
            return HttpResponse.ok();

        return HttpResponse.unauthorized();


    }
}
