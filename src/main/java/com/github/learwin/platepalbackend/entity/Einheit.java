package com.github.learwin.platepalbackend.entity;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

@Serdeable
@Entity
public class Einheit {
    @Id
    @GeneratedValue(GeneratedValue.Type.AUTO)
    private Long id;

    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String abkuerzung;

    public Einheit() {
    }

    public Einheit(String name, String abkuerzung) {
        this.name = name;
        this.abkuerzung = abkuerzung;
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

    public @Size(max = 255) String getAbkuerzung() {
        return abkuerzung;
    }

    public void setAbkuerzung(@Size(max = 255) String abkuerzung) {
        abkuerzung = abkuerzung;
    }
}
