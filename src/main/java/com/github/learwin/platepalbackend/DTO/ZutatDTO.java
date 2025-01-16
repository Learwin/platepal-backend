package com.github.learwin.platepalbackend.DTO;

import com.github.learwin.platepalbackend.entity.Einheit;
import com.github.learwin.platepalbackend.entity.Zutat;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class ZutatDTO {

    private int id;
    private int menge;
    private Einheit einheit;

    public ZutatDTO(int id, int menge, Einheit einheit) {
        this.id = id;
        this.menge = menge;
        this.einheit = einheit;
    }

    public int getZutat() {
        return id;
    }

    public void setZutat(int zutat) {
        this.id = zutat;
    }

    public int getMenge() {
        return menge;
    }

    public void setMenge(int menge) {
        this.menge = menge;
    }

    public Einheit getEinheit() {
        return einheit;
    }

    public void setEinheit(Einheit einheit) {
        this.einheit = einheit;
    }

}
