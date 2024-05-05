package com.TuneWave.AudioApp.User;

import java.util.List;

public interface ArtistService {
    List<User> getAllArtist();
    void addArtist(User user);
    User getArtistById(Long Id);
    boolean updateArtist(User user);
    boolean deleteArtist(Long id);
}
