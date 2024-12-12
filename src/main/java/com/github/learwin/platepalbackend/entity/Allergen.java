package com.github.learwin.platepalbackend.entity;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

@Serdeable
@Entity
public class Allergen {
    @Id
    @GeneratedValue(GeneratedValue.Type.AUTO)
    private Long id;

    @Size(max = 255)
    private String name;

    public Allergen() {
    }

    public Allergen(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @Size(max = 255) String getName() {
        return name;
    }

    public void setName(@Size(max = 255) String name) {
        this.name = name;
    }
}
