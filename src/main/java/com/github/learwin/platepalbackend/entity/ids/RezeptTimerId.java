package com.github.learwin.platepalbackend.entity.ids;

import com.github.learwin.platepalbackend.entity.Rezept;
import com.github.learwin.platepalbackend.entity.Timer;
import io.micronaut.data.annotation.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.Objects;;

@Embeddable
@Table(name = "Rezept_Timer")
public class RezeptTimerId {

    @ManyToOne
    @JoinColumn(name = "timer_id", referencedColumnName = "zeit")
    private Timer timer_id;

    @ManyToOne
    @JoinColumn(name = "rezept_id", referencedColumnName = "id")
    private Rezept rezept_id;

    public RezeptTimerId(){

    }

    public RezeptTimerId (Timer timerId, Rezept rezeptId){
        this.timer_id = timerId;
        this.rezept_id = rezeptId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RezeptTimerId that = (RezeptTimerId) o;
        return Objects.equals(timer_id, that.timer_id) && Objects.equals(rezept_id, that.rezept_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timer_id, rezept_id);
    }
}

