package com.TuneWave.AudioApp.Artist;

import java.util.List;

public interface ArtistService {
    List<Artist> getAllArtist();
    void addArtist(Artist artist);
    Artist getArtistById(Long Id);
    boolean updateArtist(Artist artist);
    boolean deleteArtist(Long id);
}
