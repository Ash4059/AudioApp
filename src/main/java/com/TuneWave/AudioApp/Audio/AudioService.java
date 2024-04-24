package com.TuneWave.AudioApp.Audio;

import java.util.List;

public interface AudioService {
    List<Audio> findAll();
    void createAudio(Audio audio);
    Audio getAudioById(Long id);
    boolean deleteAudio(Long id);
    boolean updateAudio(Audio audio);
}
