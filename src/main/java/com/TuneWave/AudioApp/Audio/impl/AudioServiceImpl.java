package com.TuneWave.AudioApp.Audio.impl;

import com.TuneWave.AudioApp.Audio.Audio;
import com.TuneWave.AudioApp.Audio.AudioRepository;
import com.TuneWave.AudioApp.Audio.AudioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AudioServiceImpl implements AudioService {

    AudioRepository audioRepository;

    public AudioServiceImpl(AudioRepository audioRepository){
        this.audioRepository = audioRepository;
    }

    @Override
    public List<Audio> findAll() {
        return audioRepository.findAll();
    }

    @Override
    public void createAudio(Audio audio) {
        audioRepository.save(audio);
    }

    @Override
    public Audio getAudioById(Long id){
        return audioRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteAudio(Long id) {
        try{
            audioRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updateAudio(Audio audio) {
        Optional<Audio> audioOptional = audioRepository.findById(audio.getId());
        if(audioOptional.isPresent()){
            Audio oAudio = audioOptional.get();
            oAudio.setTitle(audio.getTitle());
            oAudio.setDescription(audio.getDescription());
            oAudio.setImageUrl(audio.getImageUrl());
            oAudio.setAudioUrl(audio.getAudioUrl());
            oAudio.setDuration(audio.getDuration());
            audioRepository.save(oAudio);
            return true;
        }
        return false;
    }
}
