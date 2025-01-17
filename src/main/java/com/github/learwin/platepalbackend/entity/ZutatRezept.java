package com.github.learwin.platepalbackend.entity;

import com.github.learwin.platepalbackend.entity.ids.ZutatRezeptId;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;

@Entity
@Table(name = "Zutat_Rezept")
@Serdeable
public class ZutatRezept {

    @EmbeddedId
    private ZutatRezeptId id;

    private int menge;

    @ManyToOne
    @JoinColumn(name = "einheit_id", referencedColumnName = "id")
    private Einheit einheit_id;

    public ZutatRezept(){

    }

    public ZutatRezept(int menge, Einheit einheit_id) {
        this.menge = menge;
        this.einheit_id = einheit_id;
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

    public ZutatRezeptId getId() {
        return id;
    }

    public void setId(ZutatRezeptId id) {
        this.id = id;
    }
}
