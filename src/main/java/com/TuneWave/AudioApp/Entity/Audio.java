package com.TuneWave.AudioApp.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Audio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String title;
    String description;
    Duration duration = Duration.of(0, ChronoUnit.MINUTES);
    String imageUrl;
    String audioUrl;

    @JsonBackReference
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "audio")
    private List<Review> reviews;
    
    public Audio(){

    }

    public Audio(String title, long id, String description, String duration, String imageUrl, String audioUrl) {
        this.id = id;
        this.title = title;
        this.description = description;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime time = LocalTime.parse(duration, formatter);
        this.duration = this.duration.plusMinutes(time.getMinute()).plusSeconds(time.getSecond());
        this.imageUrl = imageUrl;
        this.audioUrl = audioUrl;
        this.reviews = new LinkedList<>();
    }
    
    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }
}
