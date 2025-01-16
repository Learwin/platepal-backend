package com.github.learwin.platepalbackend.entity;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;

@Entity
@Table(name = "Rezept_Timer")
@Serdeable
public class RezeptTimer {

    @ManyToOne
    @JoinColumn(name = "timer_id", referencedColumnName = "id")
    private Timer timer_id;

    @ManyToOne
    @JoinColumn(name = "rezept_id", referencedColumnName = "id")
    private Rezept rezept_id;

    @Column(name = "position")
    private int position;

    public RezeptTimer(){
    }

    public RezeptTimer(Rezept rezept_id, Timer timer_id, int position) {
        this.rezept_id = rezept_id;
        this.timer_id = timer_id;
        this.position = position;
    }

    public Timer getTimer_id() {
        return timer_id;
    }

    public void setTimer_id(Timer timer_id) {
        this.timer_id = timer_id;
    }

    public Rezept getRezept_id() {
        return rezept_id;
    }

    public void setRezept_id(Rezept rezept_id) {
        this.rezept_id = rezept_id;
    }

    public int getPosition() {
        return position;
    }
    public void setPosition(int position) {
        this.position = position;
    }
}
