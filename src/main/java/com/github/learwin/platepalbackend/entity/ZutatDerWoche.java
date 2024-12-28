package com.github.learwin.platepalbackend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.learwin.platepalbackend.PlatePalConstants;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.*;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.time.LocalDate;

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
    private LocalDate von;

    @NonNull
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = PlatePalConstants.TIME_FORMAT)
    private LocalDate bis;

    public ZutatDerWoche() {
    }

    public ZutatDerWoche(Zutat zutat, LocalDate von, LocalDate bis) {
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

    public @NonNull LocalDate getVon() {
        return von;
    }

    public void setVon(@NonNull LocalDate von) {
        this.von = von;
    }

    public @NonNull LocalDate getBis() {
        return bis;
    }

    public void setBis(@NonNull LocalDate bis) {
        this.bis = bis;
    }

    public Zutat getZutat() {
        return zutat;
    }

    public void setZutat(Zutat zutat) {
        this.zutat = zutat;
    }
}
