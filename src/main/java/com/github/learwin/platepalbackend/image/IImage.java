package com.github.learwin.platepalbackend.image;

import jakarta.validation.constraints.Size;

public interface IImage {

    @Size(max = 255) String getFoto();

    void setFoto(@Size(max = 255) String foto);

}
