package com.github.learwin.platepalbackend.entity.ids;

import io.micronaut.data.annotation.Embeddable;

import java.util.Objects;

@Embeddable
public class RezeptTimerId {
    private long timerId;
    private long rezeptId;

    public RezeptTimerId (long timerId, long rezeptId){
        this.timerId = timerId;
        this.rezeptId = rezeptId;
    }


    public long getTimerId() {
        return timerId;
    }

    public void setTimerId(long timerId) {
        this.timerId = timerId;
    }

    public long getRezeptId() {
        return rezeptId;
    }

    public void setRezeptId(long rezeptId) {
        this.rezeptId = rezeptId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZutatRezeptId that = (ZutatRezeptId) o;
        return Objects.equals(timerId, that.timer_id) && Objects.equals(rezeptId, that.rezeptId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timerId, rezeptId);
    }
}

