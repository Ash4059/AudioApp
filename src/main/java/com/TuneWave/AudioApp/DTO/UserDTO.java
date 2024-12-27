package com.TuneWave.AudioApp.DTO;

import java.util.*;

import com.TuneWave.AudioApp.Country;
import com.TuneWave.AudioApp.Entity.User;;

public class UserDTO {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public boolean isArtist() {
        return isArtist;
    }

    public void setArtist(boolean artist) {
        isArtist = artist;
    }

    private String emailId;
    private Date birthDate;
    private Country country;
    private boolean isArtist;

    public UserDTO( User user) {
        this.id = user.getId();
        this.username = user.getUserName();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.emailId = user.getEmailId();
        this.birthDate = user.getBirthDate();
        this.country = user.getCountry();
        this.isArtist = user.isArtist();
    }
}
