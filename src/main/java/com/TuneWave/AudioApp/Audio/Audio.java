package com.TuneWave.AudioApp.Audio;


import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Audio {
    long id;
    String title;
    String description;
    Duration timeSpan = Duration.of(0, ChronoUnit.MINUTES);
    String artistId;
    String imageUrl;
    String audioUrl;

    public Audio(String title, long id, String description, String timeSpan, String artistId, String imageUrl, String audioUrl) {
        this.title = title;
        this.id = id;
        this.description = description;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime time = LocalTime.parse(timeSpan, formatter);
        this.timeSpan = this.timeSpan.plusMinutes(time.getMinute()).plusSeconds(time.getSecond());
        this.artistId = artistId;
        this.imageUrl = imageUrl;
        this.audioUrl = audioUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Duration getTimeSpan() {
        return timeSpan;
    }

    public void setTimeSpan(Duration timeSpan) {
        this.timeSpan = timeSpan;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
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
