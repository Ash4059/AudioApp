package com.TuneWave.AudioApp.Service;

import com.TuneWave.AudioApp.Entity.Audio;

import java.util.List;

public interface AudioService {
    List<Audio> findAll();
    boolean createAudio(Audio audio);
    Audio getAudioById(Long id);
    boolean deleteAudio(Long id);
    boolean updateAudio(Audio audio);
}
