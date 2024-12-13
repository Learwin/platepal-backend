package com.github.learwin.platepalbackend.entity;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Serdeable
@Entity
public class Timer {
    @Id
    Float zeit;

    public Float getZeit() {
        return zeit;
    }

    public void setZeit(Float zeit) {
        this.zeit = zeit;
    }
}
