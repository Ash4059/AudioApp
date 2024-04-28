package com.TuneWave.AudioApp.Audio.impl;

import com.TuneWave.AudioApp.Audio.Audio;
import com.TuneWave.AudioApp.Audio.AudioService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AudioServiceImpl implements AudioService {

    private final List<Audio> Audios = new ArrayList<>();
    private static long nextId = 1;

    @Override
    public List<Audio> findAll() {
        return Audios;
    }

    int getIdxById(Long id) {
        for (int i = 0; i < Audios.size(); i++) {
            if (Audios.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
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
        return Audios.removeIf(audio -> audio.getId() == id);
    }

    @Override
    public boolean updateAudio(Audio audio) {
        int idx = getIdxById(audio.getId());
        if (idx == -1) {
            return false;
        }
        Audios.set(idx, audio);
        return true;
    }
}
