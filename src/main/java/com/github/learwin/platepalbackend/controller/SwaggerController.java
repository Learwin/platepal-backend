package com.github.learwin.platepalbackend.controller;

import com.github.learwin.platepalbackend.PlatePalConstants;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.swagger.v3.oas.annotations.Hidden;

@Controller("/swagger")
public class SwaggerController {

    @Get
    @Hidden
    HttpResponse<?> swagger() {
        return HttpResponse.seeOther(PlatePalConstants.SWAGGER_UI);
    }



}
