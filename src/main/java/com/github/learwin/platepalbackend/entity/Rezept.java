package com.github.learwin.platepalbackend.entity;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Serdeable
@Entity
public class Rezept {
    @Id
    @GeneratedValue(GeneratedValue.Type.AUTO)
    private Long id;

    @Lob
    @Column(nullable = false)
    private String anweisungen;

    private int zeit;
    private int schwierigkeit;

    @Column(name = "default_portionen")
    private int defaultPortionen;

    @Size(max = 255)
    private String foto;

    private int userId;

    @Column(name = "durchschnittliche_bewertung")
    private Float durchschnittlicheBewertung;

    public Rezept() {
    }

    public Rezept(String anweisungen, int zeit, int schwierigkeit, int defaultPortionen, String foto, int userId, Float durchschnittlicheBewertung) {
        this.anweisungen = anweisungen;
        this.zeit = zeit;
        this.schwierigkeit = schwierigkeit;
        this.defaultPortionen = defaultPortionen;
        this.foto = foto;
        this.userId = userId;
        this.durchschnittlicheBewertung = durchschnittlicheBewertung;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnweisungen() {
        return anweisungen;
    }

    public void setAnweisungen(String anweisungen) {
        this.anweisungen = anweisungen;
    }

    public int getZeit() {
        return zeit;
    }

    public void setZeit(int zeit) {
        this.zeit = zeit;
    }

    public int getSchwierigkeit() {
        return schwierigkeit;
    }

    public void setSchwierigkeit(int schwierigkeit) {
        this.schwierigkeit = schwierigkeit;
    }

    public int getDefaultPortionen() {
        return defaultPortionen;
    }

    public void setDefaultPortionen(int defaultPortionen) {
        this.defaultPortionen = defaultPortionen;
    }

    public @Size(max = 255) String getFoto() {
        return foto;
    }

    public void setFoto(@Size(max = 255) String foto) {
        this.foto = foto;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Float getDurchschnittlicheBewertung() {
        return durchschnittlicheBewertung;
    }

    public void setDurchschnittlicheBewertung(Float durchschnittlicheBewertung) {
        this.durchschnittlicheBewertung = durchschnittlicheBewertung;
    }
}
