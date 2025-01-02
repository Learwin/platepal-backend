//package com.github.learwin.platepalbackend.DTO;
//
//import com.github.learwin.platepalbackend.entity.Einheit;
//import com.github.learwin.platepalbackend.entity.Rezept;
//import com.github.learwin.platepalbackend.entity.Zutat;
//import io.micronaut.core.annotation.Introspected;
//
//import java.util.List;
//
//
//public static class ZutatRezeptDto {
//
//    private Rezept rezept;
//    private List<Zutat> zutatList;
//    private int menge;
//    private Einheit einheit;
//
//    // Constructor
////    public ZutatRezeptDto(Rezept rezept, List<Zutat> zutatList, int menge) {
////        this.rezept = rezept;
////        this.zutatList = zutatList;
////        this.menge = menge;
////    }
//
//    // Getters and Setters
//    public Rezept getRezept() {
//        return rezept;
//    }
//
//    public void setRezept(Rezept rezept) {
//        this.rezept = rezept;
//    }
//
//    public List<Zutat> getZutatList() {
//        return zutatList;
//    }
//
//    public void setZutatList(List<Zutat> zutatList) {
//        this.zutatList = zutatList;
//    }
//
//    public int getMenge() {
//        return menge;
//    }
//
//    public void setMenge(int menge) {
//        this.menge = menge;
//    }
//
//    public Einheit getEinheit() {
//        return einheit;
//    }
//
//    public void setEinheit(Einheit einheit) {
//        this.einheit = einheit;
//    }
//}