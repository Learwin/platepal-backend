package com.github.learwin.platepalbackend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.learwin.platepalbackend.PlatePalConstants;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.*;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Serdeable
@MappedEntity
@Table(name = "zutatderwoche")
public class ZutatDerWoche {

    @Id
    @GeneratedValue(GeneratedValue.Type.AUTO)
    private Long id;

    @JoinColumn(name = "zutat_id", referencedColumnName = "id")
    @Relation(value = Relation.Kind.MANY_TO_ONE)
    @MappedProperty(value = "zutat_id")
    private Zutat zutat;

    @NonNull
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = PlatePalConstants.TIME_FORMAT)
    private LocalDateTime von;

    @NonNull
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = PlatePalConstants.TIME_FORMAT)
    private LocalDateTime bis;

    public ZutatDerWoche() {
    }

    public ZutatDerWoche(Zutat zutat, LocalDateTime von, LocalDateTime bis) {
        this.zutat = zutat;
        this.von = von;
        this.bis = bis;
    }

    public @NonNull Long getId() {
        return id;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
    }

    public @NonNull LocalDateTime getVon() {
        return von;
    }

    public void setVon(@NonNull LocalDateTime von) {
        this.von = von;
    }

    public @NonNull LocalDateTime getBis() {
        return bis;
    }

    public void setBis(@NonNull LocalDateTime bis) {
        this.bis = bis;
    }

    public Zutat getZutat() {
        return zutat;
    }

    public void setZutat(Zutat zutat) {
        this.zutat = zutat;
    }
}
