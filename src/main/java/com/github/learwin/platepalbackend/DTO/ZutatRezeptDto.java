package com.github.learwin.platepalbackend.DTO;

import com.github.learwin.platepalbackend.entity.Rezept;
import com.github.learwin.platepalbackend.entity.Zutat;
import com.github.learwin.platepalbackend.entity.ZutatMengeEinheitAllergen;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Introspected
@Serdeable
public class ZutatRezeptDto {

    private Rezept rezept;
    private ArrayList<ZutatMengeEinheitAllergen> zutatMengeList;

    // Constructor
    public ZutatRezeptDto(Rezept rezept, ArrayList<ZutatMengeEinheitAllergen> zutatList) {
        this.rezept = rezept;
        this.zutatMengeList = zutatList;
    }

    // Getters and Setters
    public Rezept getRezept() {
        return rezept;
    }

    public void setRezept(Rezept rezept) {
        this.rezept = rezept;
    }

    public ArrayList<ZutatMengeEinheitAllergen> getZutatMengeList() {
        return zutatMengeList;
    }

    public void setZutatMengeList(ArrayList<ZutatMengeEinheitAllergen> zutatMengeList) {
        this.zutatMengeList = zutatMengeList;
    }
}