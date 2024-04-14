package com.TuneWave.AudioApp.Audio.impl;

import com.TuneWave.AudioApp.Audio.Audio;
import com.TuneWave.AudioApp.Audio.AudioService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AudioServiceImpl implements AudioService {

    private List<Audio> Audios = new ArrayList<>();
    private static long nextId = 1;

    @Override
    public List<Audio> findAll() {
        return Audios;
    }

    @Override
    public void createAudio(Audio audio) {
        audio.setId(nextId++);
        Audios.add(audio);
    }

    @Override
    public Audio getAudioById(Long id){
        return Audios.stream().filter(audio -> audio.getId() == id).findFirst().orElse(null);
    }

    @Override
    public boolean deleteAudio(Long id) {
        Audios.removeIf(audio -> audio.getId() == id);
    }
}
