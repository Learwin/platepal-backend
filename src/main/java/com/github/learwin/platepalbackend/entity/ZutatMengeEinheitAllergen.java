package com.github.learwin.platepalbackend.entity;

import io.micronaut.serde.annotation.Serdeable;

import java.util.List;

@Serdeable
public class ZutatMengeEinheitAllergen {
    private Zutat zutat;
    private List<Allergen> allergene;
    private int menge;
    private Einheit einheit;

    public ZutatMengeEinheitAllergen(){
    }

    public ZutatMengeEinheitAllergen(Zutat zutat, List<Allergen> allergene, int menge, Einheit einheit) {
        this.zutat = zutat;
        this.allergene = allergene;
        this.menge = menge;
        this.einheit = einheit;
    }

    public List<Allergen> getAllergene() {
        return allergene;
    }

    public void setAllergene(List<Allergen> allergene) {
        this.allergene = allergene;
    }

    public int getMenge() {
        return menge;
    }

    public void setMenge(int menge) {
        this.menge = menge;
    }

    public Einheit getEinheit() {
        return einheit;
    }

    public void setEinheit(Einheit einheit) {
        this.einheit = einheit;
    }

    public Zutat getZutat() {
        return zutat;
    }

    public void setZutat(Zutat zutat) {
        this.zutat = zutat;
    }
}
