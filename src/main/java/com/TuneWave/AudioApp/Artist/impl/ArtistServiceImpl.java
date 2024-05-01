package com.TuneWave.AudioApp.Artist.impl;

import com.TuneWave.AudioApp.Artist.Artist;
import com.TuneWave.AudioApp.Artist.ArtistRepository;
import com.TuneWave.AudioApp.Artist.ArtistService;
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
    public List<Artist> getAllArtist() {
        return artistRepository.findAll();
    }

    @Override
    public void addArtist(Artist artist) {
        artistRepository.save(artist);
    }

    @Override
    public Artist getArtistById(Long Id) {
        Optional<Artist> artistOptional = artistRepository.findById(Id);
        return artistOptional.orElse(null);
    }

    @Override
    public boolean updateArtist(Artist artist) {
        Optional<Artist> artistOptional = artistRepository.findById(artist.getId());
        if(artistOptional.isPresent()){
            Artist oArtist = artistOptional.get();
            oArtist.setName(artist.getName());
            oArtist.setAge(artist.getAge());
            oArtist.setCountry(artist.getCountry());
            artistRepository.save(oArtist);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteArtist(Long Id) {
        Optional<Artist> artistOptional = artistRepository.findById(Id);
        if(artistOptional.isPresent()){
            artistRepository.deleteById(Id);
            return  true;
        }
        return false;
    }
}
