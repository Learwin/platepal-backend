package com.github.learwin.platepalbackend.entity;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;

@Entity
@Table(name = "Zutat_Rezept")
@Serdeable
public class ZutatRezept {

    @Id
    @GeneratedValue(GeneratedValue.Type.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "zutat_id", referencedColumnName = "id")
    private Zutat zutat_id;

    @ManyToOne
    @JoinColumn(name = "rezept_id", referencedColumnName = "id")
    private Rezept rezept_id;

    private int menge;

    @ManyToOne
    @JoinColumn(name = "einheit_id", referencedColumnName = "id")
    private Einheit einheit_id;

    public ZutatRezept(){

    }

    public ZutatRezept(Rezept rezept_id, Zutat zutat_id, int menge, Einheit einheit_id) {
        this.rezept_id = rezept_id;
        this.zutat_id = zutat_id;
        this.menge = menge;
        this.einheit_id = einheit_id;
    }

    public Zutat getZutat_id() {
        return zutat_id;
    }

    public void setZutat_id(Zutat zutat_id) {
        this.zutat_id = zutat_id;
    }

    public Rezept getRezept_id() {
        return rezept_id;
    }

    public void setRezept_id(Rezept rezept_id) {
        this.rezept_id = rezept_id;
    }

    public int getMenge() {
        return menge;
    }

    public void setMenge(int menge) {
        this.menge = menge;
    }

    public Einheit getEinheit_id() {
        return einheit_id;
    }

    public void setEinheit_id(Einheit einheit_id) {
        this.einheit_id = einheit_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
