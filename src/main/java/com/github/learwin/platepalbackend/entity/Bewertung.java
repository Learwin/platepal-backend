package com.github.learwin.platepalbackend.entity;

import com.github.learwin.platepalbackend.image.IImage;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.Relation;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Serdeable
@MappedEntity
public class Bewertung implements IImage {

    @Id
    @GeneratedValue(GeneratedValue.Type.AUTO)
    private Long id;

    private Long anzahl_Sterne;

    @Lob
    private String kommentar;

    @Size(max = 255)
    private String foto;

    @OneToOne
    @JoinColumn(name = "rezept_id", referencedColumnName = "id")
    private Rezept rezept_id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user_Id;

    public  Bewertung(){
    }

    public Bewertung(Long anzahlSterne, String kommentar, String foto, Rezept rezeptId, User userId) {
        this.anzahl_Sterne = anzahlSterne;
        this.kommentar = kommentar;
        this.foto = foto;
        this.rezept_id = rezeptId;
        this.user_Id = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAnzahl_Sterne() {
        return anzahl_Sterne;
    }

    public void setAnzahl_Sterne(Long anzahl_Sterne) {
        this.anzahl_Sterne = anzahl_Sterne;
    }

    public String getKommentar() {
        return kommentar;
    }

    public void setKommentar(String kommentar) {
        this.kommentar = kommentar;
    }

    @Override
    public @Size(max = 255) String getFoto() {
        return foto;
    }

    @Override
    public void setFoto(@Size(max = 255) String foto) {
        this.foto = foto;
    }

    public Rezept getRezept_id() {
        return rezept_id;
    }

    public void setRezept_id(Rezept rezept_id) {
        this.rezept_id = rezept_id;
    }

    public User getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(User user_Id) {
        this.user_Id = user_Id;
    }
}
