package com.github.learwin.platepalbackend.entity;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.sql.JoinColumn;
import io.micronaut.data.annotation.sql.JoinTable;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Size;

import java.util.List;

@Serdeable
@Entity
public class Allergen {
    @Id
    @GeneratedValue(GeneratedValue.Type.AUTO)
    private Long id;

    @Size(max = 255)
    private String name;

    @ManyToMany(mappedBy = "allergene")
    private List<Zutat> zutaten;

    public Allergen() {
    }

    public Allergen(String name, List<Zutat> zutaten) {
        this.name = name;
        this.zutaten = zutaten;
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

    public List<Zutat> getZutaten() {
        return zutaten;
    }

    public void setZutaten(List<Zutat> zutaten) {
        this.zutaten = zutaten;
    }
}
