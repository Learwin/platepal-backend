package com.github.learwin.platepalbackend.entity;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Serdeable
@Entity
@Table(name = "usertable")
public class User {
    @Id
    @GeneratedValue(GeneratedValue.Type.AUTO)
    private Long id;

    @Column(nullable = false)
    @Size(max = 255)
    private String username;

    @Column(nullable = false)
    @Size(max = 255)
    private String passwort;

    @Column(nullable = false)
    @Size(max = 255)
    private String emailAdresse;

    public User(){
    }

    public User(String username, String passwort, String emailAdresse){
        this.username = username;
        this.passwort = passwort;
        this.emailAdresse = emailAdresse;
    }

    public @NonNull Long getId() {
        return id;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
    }

    public @Size(max = 255) String getUsername() {
        return username;
    }

    public void setUsername(@Size(max = 255) String username) {
        this.username = username;
    }

    public @Size(max = 255) String getPasswort() {
        return passwort;
    }

    public void setPasswort(@Size(max = 255) String passwort) {
        this.passwort = passwort;
    }

    public @Size(max = 255) String getEmailAdresse() {
        return emailAdresse;
    }

    public void setEmailAdresse(@Size(max = 255) String emailAdresse) {
        this.emailAdresse = emailAdresse;
    }
}
