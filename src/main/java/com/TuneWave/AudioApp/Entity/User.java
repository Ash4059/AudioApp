package com.TuneWave.AudioApp.Entity;

import com.TuneWave.AudioApp.Country;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.*;

@Entity
public class User {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String userName;

    @Column(unique = true, length = 100, nullable = false)
    private String emailId;

    @Column(nullable = false)
    private transient String password;
    boolean isArtist = false;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthDate;
    private Country country;
    private static BCryptPasswordEncoder bCryptPasswordEncoder;

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<Audio> audio;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<UserRole> roleSet = new HashSet<>();

    public long getId() {
        return id;
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

    public List<Audio> getAudio() {
        return this.audio;
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
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isArtist() {
        return isArtist;
    }

    public void setArtist(boolean artist) {
        this.isArtist = artist;
    }

    public Set<UserRole> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<UserRole> roleSet) {
        this.roleSet = roleSet;
    }

    public User() {
    }

    public User(long id, String firstName, String lastName, String userName, String emailId, String password,
            Date birthDate, Country country) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.emailId = emailId;
        this.password = password;
        this.birthDate = birthDate;
        this.country = country;
        this.audio = new LinkedList<>();
    }

    public static BCryptPasswordEncoder getPasswordEncoder(){
        if(bCryptPasswordEncoder == null){
            try {
                bCryptPasswordEncoder = new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2B, 12,
                        SecureRandom.getInstance("SHA1PRNG","SUN"));
            } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
                throw new RuntimeException(e);
            }
        }
        return bCryptPasswordEncoder;
    }
}
