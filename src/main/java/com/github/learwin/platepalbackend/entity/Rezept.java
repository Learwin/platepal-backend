package com.github.learwin.platepalbackend.entity;

import com.github.learwin.platepalbackend.image.IImage;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Serdeable
@Entity
public class Rezept implements IImage {
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

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user_Id;

    @Size(max = 255)
    private String name;

    @Column(name = "durchschnittliche_bewertung")
    private Float durchschnittlicheBewertung;

    private int flag;

//    @OneToMany
//    @JoinTable(name = "zutat_rezept",
//            joinColumns = @JoinColumn(name = "rezept_id"),
//            inverseJoinColumns = @JoinColumn(name = "zutat_id"))
//    private List<ZutatRezept> zutatenliste;

    public Rezept() {
    }

    public Rezept(String anweisungen, int zeit, int schwierigkeit, int defaultPortionen, String foto, User user_Id, Float durchschnittlicheBewertung, int flag, String name) {
        this.anweisungen = anweisungen;
        this.zeit = zeit;
        this.schwierigkeit = schwierigkeit;
        this.defaultPortionen = defaultPortionen;
        this.foto = foto;
        this.name = name;
        this.user_Id = user_Id;
        this.durchschnittlicheBewertung = durchschnittlicheBewertung;
        this.flag = flag;
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

    public User getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(User user_Id) {
        this.user_Id = user_Id;
    }

    public Float getDurchschnittlicheBewertung() {
        return durchschnittlicheBewertung;
    }

    public void setDurchschnittlicheBewertung(Float durchschnittlicheBewertung) {
        this.durchschnittlicheBewertung = durchschnittlicheBewertung;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public @Size(max = 255) String getName() {
        return name;
    }

    public void setName(@Size(max = 255) String name) {
        this.name = name;
    }
}
