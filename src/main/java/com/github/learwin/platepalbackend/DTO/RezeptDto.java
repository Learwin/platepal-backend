package com.github.learwin.platepalbackend.DTO;

import com.github.learwin.platepalbackend.entity.Einheit;
import com.github.learwin.platepalbackend.entity.Rezept;
import com.github.learwin.platepalbackend.entity.Zutat;

import java.util.List;

public class RezeptDto {
        private String name;
        private List<ZutatRezeptDto> zutaten;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ZutatRezeptDto> getZutaten() {
        return zutaten;
    }

    public void setZutaten(List<ZutatRezeptDto> zutaten) {
        this.zutaten = zutaten;
    }

    public static class ZutatRezeptDto {

        private Rezept rezept;
        private Zutat zutat;
        private int menge;
        private Einheit einheit;

        // Constructor
//    public ZutatRezeptDto(Rezept rezept, List<Zutat> zutatList, int menge) {
//        this.rezept = rezept;
//        this.zutatList = zutatList;
//        this.menge = menge;
//    }

        // Getters and Setters
        public Rezept getRezept() {
            return rezept;
        }

        public void setRezept(Rezept rezept) {
            this.rezept = rezept;
        }

        public Zutat getZutat() {
            return zutat;
        }

        public void setZutat(Zutat zutat) {
            this.zutat = zutat;
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
}
