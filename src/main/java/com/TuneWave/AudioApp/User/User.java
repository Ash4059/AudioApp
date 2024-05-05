package com.TuneWave.AudioApp.User;

import com.TuneWave.AudioApp.Audio.Audio;
import com.TuneWave.AudioApp.Country;
import com.TuneWave.AudioApp.Reviews.Review;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String LastName;
    private String userName;
    private String emailId;
    private String Password;
    boolean isArtist = false;
    private int age;
    private Country country;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Audio> audio;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Review> reviews;

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public long getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Audio> getAudio() {
        return audio;
    }

    public void setAudio(List<Audio> audio) {
        this.audio = audio;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public boolean isArtist() {
        return isArtist;
    }

    public void setArtist(boolean artist) {
        isArtist = artist;
    }

    public User() {
    }

    public User(long id, String firstName, String lastName, String userName, String emailId, String password,
            int age, Country country) {
        this.id = id;
        this.firstName = firstName;
        LastName = lastName;
        this.userName = userName;
        this.emailId = emailId;
        Password = password;
        this.age = age;
        this.country = country;
    }

    public User(long id, String firstName, String lastName, String userName, String emailId, String password,
            boolean isArtist, int age, Country country) {
        this.id = id;
        this.firstName = firstName;
        LastName = lastName;
        this.userName = userName;
        this.emailId = emailId;
        Password = password;
        this.isArtist = isArtist;
        this.age = age;
        this.country = country;
    }
}
