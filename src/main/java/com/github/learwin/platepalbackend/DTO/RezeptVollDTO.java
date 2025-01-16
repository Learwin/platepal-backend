package com.github.learwin.platepalbackend.DTO;

import com.github.learwin.platepalbackend.entity.User;
import com.github.learwin.platepalbackend.entity.Zutat;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Size;

import java.util.List;

@Serdeable
public class RezeptVollDTO {

    private Long id;
    private String anweisungen;
    private Integer zeit;
    private Integer schwierigkeit;
    private Integer defaultPortionen;
    private User user;
    private String name;
    private Float durchschnittlicheBewertung;
    private Integer flag;

    private List<ZutatDTO> zutaten;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnweisungen() {
        return anweisungen;
    }

    public void setAnweisungen(String anweisungen) {
        this.anweisungen = anweisungen;
    }

    public Integer getZeit() {
        return zeit;
    }

    public void setZeit(Integer zeit) {
        this.zeit = zeit;
    }

    public Integer getSchwierigkeit() {
        return schwierigkeit;
    }

    public void setSchwierigkeit(Integer schwierigkeit) {
        this.schwierigkeit = schwierigkeit;
    }

    public Integer getDefaultPortionen() {
        return defaultPortionen;
    }

    public void setDefaultPortionen(Integer defaultPortionen) {
        this.defaultPortionen = defaultPortionen;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getDurchschnittlicheBewertung() {
        return durchschnittlicheBewertung;
    }

    public void setDurchschnittlicheBewertung(Float durchschnittlicheBewertung) {
        this.durchschnittlicheBewertung = durchschnittlicheBewertung;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public List<ZutatDTO> getZutaten() {
        return zutaten;
    }

    public void setZutaten(List<ZutatDTO> zutaten) {
        this.zutaten = zutaten;
    }

}
