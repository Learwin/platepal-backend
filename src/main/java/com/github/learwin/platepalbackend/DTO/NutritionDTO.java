package com.github.learwin.platepalbackend.DTO;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class NutritionDTO {

    public float getKcal() {
        return kcal;
    }

    public void setKcal(float kcal) {
        this.kcal = kcal;
    }

    public float getFett() {
        return fett;
    }

    public void setFett(float fett) {
        this.fett = fett;
    }

    public float getGesaettigteFettsaeuren() {
        return gesaettigteFettsaeuren;
    }

    public void setGesaettigteFettsaeuren(float gesaettigteFettsaeuren) {
        this.gesaettigteFettsaeuren = gesaettigteFettsaeuren;
    }

    public float getKohlenhydrate() {
        return kohlenhydrate;
    }

    public void setKohlenhydrate(float kohlenhydrate) {
        this.kohlenhydrate = kohlenhydrate;
    }

    public float getZucker() {
        return zucker;
    }

    public void setZucker(float zucker) {
        this.zucker = zucker;
    }

    public float getBallaststoffe() {
        return ballaststoffe;
    }

    public void setBallaststoffe(float ballaststoffe) {
        this.ballaststoffe = ballaststoffe;
    }

    public float getEiweiss() {
        return eiweiss;
    }

    public void setEiweiss(float eiweiss) {
        this.eiweiss = eiweiss;
    }

    public float getSalz() {
        return salz;
    }

    public void setSalz(float salz) {
        this.salz = salz;
    }

    private float kcal;
    private float fett;
    private float gesaettigteFettsaeuren;
    private float kohlenhydrate;
    private float zucker;
    private float ballaststoffe;
    private float eiweiss;
    private float salz;

    public NutritionDTO(){

    }

    public NutritionDTO(float kcal, float fett, float gesaettigteFettsaeuren, float kohlenhydrate, float zucker, float ballaststoffe, float eiweiss, float salz) {
        this.kcal = kcal;
        this.fett = fett;
        this.gesaettigteFettsaeuren = gesaettigteFettsaeuren;
        this.kohlenhydrate = kohlenhydrate;
        this.zucker = zucker;
        this.ballaststoffe = ballaststoffe;
        this.eiweiss = eiweiss;
        this.salz = salz;
    }
}
