package com.github.learwin.platepalbackend;

import io.micronaut.http.uri.UriBuilder;

import java.net.URI;

public class PlatePalConstants {

    public static final String TIME_FORMAT = "yyyy-mm-dd";

    public static final String IMAGE_DIR = "./images";
    public static final String ZUTAT_IMAGE_DIR = IMAGE_DIR + "/zutat";
    public static final String REZEPT_IMAGE_DIR = IMAGE_DIR + "/rezept";
    public static final String BEWERTUNG_IMAGE_DIR = IMAGE_DIR + "/bewertung";
    public static final String USER_IMAGE_DIR = IMAGE_DIR + "/user";

    public static final URI SWAGGER_UI = UriBuilder.of("/swagger-ui").path("index.html").build();
}
