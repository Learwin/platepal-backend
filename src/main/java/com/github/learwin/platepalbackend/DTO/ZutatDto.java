package com.github.learwin.platepalbackend.DTO;

import com.github.learwin.platepalbackend.entity.Einheit;

import java.util.List;

public class ZutatDto {
        private String name;
        private List<RezeptDTO> rezepte;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RezeptDTO> getRezepte() {
        return rezepte;
    }

    public void setRezepte(List<RezeptDTO> rezepte) {
        this.rezepte = rezepte;
    }

    public static class RezeptDTO {
            private String name;
            private int menge;
            private Einheit einheit;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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
