package com.github.learwin.platepalbackend.entity;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class TimerPosition {
    private Timer timer;
    private Integer position;

    public TimerPosition() {
    }

    public TimerPosition(Timer timer, Integer position){
        this.timer = timer;
        this.position = position;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}