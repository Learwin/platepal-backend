package com.github.learwin.platepalbackend.entity;

import com.github.learwin.platepalbackend.image.IImage;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.sql.JoinColumn;
import io.micronaut.data.annotation.sql.JoinTable;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.List;

@Serdeable
@Entity
public class Zutat implements IImage {

    @Id
    @GeneratedValue(GeneratedValue.Type.AUTO)
    private Long id;

    @Column(nullable = false)
    @Size(max = 255)
    private String name;

    private Float kcal;
    private Float fett;

    @Column(name = "GESAETTIGTE_FETTSAEUREN")
    private Float gesaettigteFettsaeuren;
    private Float kohlenhydrate;
    private Float zucker;
    private Float ballaststoffe;
    private Float eiweiss;
    private Float salz;

    @Size(max = 255)
    private String foto;

    @ManyToMany
    @JoinTable(name = "zutat_allergen",
            joinColumns = @JoinColumn(name = "zutat_id"),
            inverseJoinColumns = @JoinColumn(name = "allergen_id"))
    private List<Allergen> allergene;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "zutat_id")
//    private List<ZutatRezept> rezepte;

    public Zutat() {
    }

    public Zutat(String name, Float kcal, Float fett, Float gesaettigteFettsaeuren, Float kohlenhydrate, Float zucker, Float ballaststoffe, Float eiweiss, Float salz, String foto, List<Allergen> allergene/*, List<ZutatRezept> rezepte*/) {
        this.name = name;
        this.kcal = kcal;
        this.fett = fett;
        this.gesaettigteFettsaeuren = gesaettigteFettsaeuren;
        this.kohlenhydrate = kohlenhydrate;
        this.zucker = zucker;
        this.ballaststoffe = ballaststoffe;
        this.eiweiss = eiweiss;
        this.salz = salz;
        this.foto = foto;
        this.allergene = allergene;
        //this.rezepte = rezepte;
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

    public Float getKcal() {
        return kcal;
    }

    public void setKcal(Float kcal) {
        this.kcal = kcal;
    }

    public Float getFett() {
        return fett;
    }

    public void setFett(Float fett) {
        this.fett = fett;
    }

    public Float getGesaettigteFettsaeuren() {
        return gesaettigteFettsaeuren;
    }

    public void setGesaettigteFettsaeuren(Float gesaettigteFettsaeuren) {
        this.gesaettigteFettsaeuren = gesaettigteFettsaeuren;
    }

    public Float getKohlenhydrate() {
        return kohlenhydrate;
    }

    public void setKohlenhydrate(Float kohlenhydrate) {
        this.kohlenhydrate = kohlenhydrate;
    }

    public Float getZucker() {
        return zucker;
    }

    public void setZucker(Float zucker) {
        this.zucker = zucker;
    }

    public Float getBallaststoffe() {
        return ballaststoffe;
    }

    public void setBallaststoffe(Float ballaststoffe) {
        this.ballaststoffe = ballaststoffe;
    }

    public Float getEiweiss() {
        return eiweiss;
    }

    public void setEiweiss(Float eiweiss) {
        this.eiweiss = eiweiss;
    }

    public Float getSalz() {
        return salz;
    }

    public void setSalz(Float salz) {
        this.salz = salz;
    }

    @Override
    public @Size(max = 255) String getFoto() {
        return foto;
    }

    @Override
    public void setFoto(@Size(max = 255) String foto) {
        this.foto = foto;
    }

    public List<Allergen> getAllergene() {
        return allergene;
    }

    public void setAllergene(List<Allergen> allergene) {
        this.allergene = allergene;
    }

//    public List<ZutatRezept> getRezepte() {
//        return rezepte;
//    }
//
//    public void setRezepte(List<ZutatRezept> rezepte) {
//        this.rezepte = rezepte;
//    }
}

