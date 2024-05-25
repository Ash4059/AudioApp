package com.TuneWave.AudioApp.Reviews;

import com.TuneWave.AudioApp.Audio.Audio;
import com.TuneWave.AudioApp.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    Rating rating;
    String review;

    @JsonIgnore
    @ManyToOne
    private Audio audio;

    @JsonIgnore
    @ManyToOne
    private User user;

    public Review(){

    }

    public Review(long id,Rating rating, String review) {
        this.id = id;
        this.rating = rating;
        this.review = review;
    }

    public long getId() {
        return id;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Audio getAudio() {
        return audio;
    }

    public void setAudio(Audio audio) {
        this.audio = audio;
    }

    public User getUser() {
        return user;
    }
}
