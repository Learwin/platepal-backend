package com.github.learwin.platepalbackend.entity.ids;

import com.github.learwin.platepalbackend.entity.Rezept;
import com.github.learwin.platepalbackend.entity.Zutat;
import io.micronaut.data.annotation.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Table(name = "Zutat_Rezept")
public class ZutatRezeptId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "zutat_id", referencedColumnName = "id")
    private Zutat zutat_id;

    @ManyToOne
    @JoinColumn(name = "rezept_id", referencedColumnName = "id")
    private Rezept rezept_id;

    public ZutatRezeptId(){
    }

    public ZutatRezeptId (Zutat zutat_id, Rezept rezept_id){
        this.zutat_id = zutat_id;
        this.rezept_id = rezept_id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZutatRezeptId that = (ZutatRezeptId) o;
        return Objects.equals(zutat_id, that.zutat_id) && Objects.equals(rezept_id, that.rezept_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zutat_id, rezept_id);
    }
}
