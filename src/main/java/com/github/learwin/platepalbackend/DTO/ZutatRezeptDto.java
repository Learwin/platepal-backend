package com.github.learwin.platepalbackend.DTO;

import com.github.learwin.platepalbackend.entity.Rezept;
import com.github.learwin.platepalbackend.entity.Zutat;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

import java.util.List;

@Introspected
@Serdeable
public class ZutatRezeptDto {

    // TODO Einheit und Mengen, ALlergene hinzufügen
    private Rezept rezept;
    private List<Zutat> zutatList;

    // Constructor
    public ZutatRezeptDto(Rezept rezept, List<Zutat> zutatList) {
        this.rezept = rezept;
        this.zutatList = zutatList;
    }

    // Getters and Setters
    public Rezept getRezept() {
        return rezept;
    }

    public void setRezept(Rezept rezept) {
        this.rezept = rezept;
    }

    public List<Zutat> getZutatList() {
        return zutatList;
    }

    public void setZutatList(List<Zutat> zutatList) {
        this.zutatList = zutatList;
    }
}