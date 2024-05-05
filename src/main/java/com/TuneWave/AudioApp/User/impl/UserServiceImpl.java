package com.TuneWave.AudioApp.User.impl;

import com.TuneWave.AudioApp.User.User;
import com.TuneWave.AudioApp.User.ArtistRepository;
import com.TuneWave.AudioApp.User.ArtistService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistServiceImpl implements ArtistService {

    ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository){
        this.artistRepository = artistRepository;
    }

    @Override
    public List<User> getAllArtist() {
        return artistRepository.findAll();
    }

    @Override
    public void addArtist(User user) {
        artistRepository.save(user);
    }

    @Override
    public User getArtistById(Long Id) {
        Optional<User> artistOptional = artistRepository.findById(Id);
        return artistOptional.orElse(null);
    }

    @Override
    public boolean updateArtist(User user) {
        Optional<User> artistOptional = artistRepository.findById(user.getId());
        if(artistOptional.isPresent()){
            User oUser = artistOptional.get();
            oUser.setName(user.getName());
            oUser.setAge(user.getAge());
            oUser.setCountry(user.getCountry());
            artistRepository.save(oUser);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteArtist(Long Id) {
        Optional<User> artistOptional = artistRepository.findById(Id);
        if(artistOptional.isPresent()){
            artistRepository.deleteById(Id);
            return  true;
        }
        return false;
    }
}
