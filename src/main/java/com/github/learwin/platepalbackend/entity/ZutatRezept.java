package com.github.learwin.platepalbackend.entity;

import com.github.learwin.platepalbackend.entity.ids.ZutatRezeptId;
import jakarta.persistence.*;

@Entity
@Table(name = "Zutat_Rezept")
public class ZutatRezept {

    @Id
    private ZutatRezeptId id;

    @ManyToOne
    @MapsId("zutat_Id")
    @JoinColumn(name = "zutat_id", referencedColumnName = "id")
    private Zutat zutat_id;

    @ManyToOne
    @MapsId("rezept_Id")
    @JoinColumn(name = "rezept_id", referencedColumnName = "id")
    private Rezept rezept_id;

    private int menge;

    @ManyToOne
    @JoinColumn(name = "einheit_id", referencedColumnName = "id")
    private Einheit einheitId;

    public ZutatRezept(){

    }

    public ZutatRezept(int menge, Einheit einheitId) {
        this.menge = menge;
        this.einheitId = einheitId;
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

    public Einheit getEinheitId() {
        return einheitId;
    }

    public void setEinheitId(Einheit einheitId) {
        this.einheitId = einheitId;
    }

    public ZutatRezeptId getId() {
        return id;
    }

    public void setId(ZutatRezeptId id) {
        this.id = id;
    }
}
