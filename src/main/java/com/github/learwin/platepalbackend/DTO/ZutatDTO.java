package com.github.learwin.platepalbackend.DTO;

import com.github.learwin.platepalbackend.entity.Einheit;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class ZutatDTO {

    private Integer id;
    private Integer menge;
    private Einheit einheit;

    public ZutatDTO(Integer id, Integer menge, Einheit einheit) {
        this.id = id;
        this.menge = menge;
        this.einheit = einheit;
    }

    public Integer getZutat() {
        return id;
    }

    public void setZutat(Integer zutat) {
        this.id = zutat;
    }

    public Integer getMenge() {
        return menge;
    }

    public void setMenge(Integer menge) {
        this.menge = menge;
    }

    public Einheit getEinheit() {
        return einheit;
    }

    public void setEinheit(Einheit einheit) {
        this.einheit = einheit;
    }

}
