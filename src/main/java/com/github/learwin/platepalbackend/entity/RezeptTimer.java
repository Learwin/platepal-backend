package com.github.learwin.platepalbackend.entity;

import com.github.learwin.platepalbackend.entity.ids.RezeptTimerId;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;

@Entity
@Table(name = "Rezept_Timer")
@Serdeable
public class RezeptTimer {

    @EmbeddedId
    private RezeptTimerId id;

    @Column(name = "position_timer")
    private int position;

    public RezeptTimer(){
    }

    public RezeptTimer(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
    public void setPosition(int position) {
        this.position = position;
    }

    public RezeptTimerId getId() {
        return id;
    }

    public void setId(RezeptTimerId id) {
        this.id = id;
    }
}
