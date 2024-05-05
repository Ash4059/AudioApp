package com.TuneWave.AudioApp.User;

import com.TuneWave.AudioApp.Audio.Audio;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Audio> getAudio() {
        return audio;
    }

    public void setAudio(List<Audio> audio) {
        this.audio = audio;
    }

    private String name;
    private int age;
    private String country;

    @JsonIgnore
    @OneToMany(mappedBy = "artist")
    private List<Audio> audio;

    public Artist(){

    }

    public Artist(long id, String name, int age, String country) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.country = country;
    }
}
