package com.TuneWave.AudioApp.Artist;

import com.TuneWave.AudioApp.Audio.Audio;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Artist {
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

    @ManyToMany
    private List<Audio> audio;

    public Artist(){

    }

    public Artist(long id, String name, int age, String country, List<Audio> audio) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.country = country;
        this.audio = audio;
    }
}
