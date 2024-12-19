package com.github.learwin.platepalbackend.entity.ids;

import io.micronaut.data.annotation.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ZutatRezeptId implements Serializable {

    private long zutatId;
    private long rezeptId;

    public ZutatRezeptId (long zutatId, long rezeptId){
        this.zutatId = zutatId;
        this.rezeptId = rezeptId;
    }


    public long getZutatId() {
        return zutatId;
    }

    public void setZutatId(long zutatId) {
        this.zutatId = zutatId;
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
        return Objects.equals(zutatId, that.zutatId) && Objects.equals(rezeptId, that.rezeptId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zutatId, rezeptId);
    }
}
