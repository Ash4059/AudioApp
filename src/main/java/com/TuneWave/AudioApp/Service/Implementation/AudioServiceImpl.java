package com.TuneWave.AudioApp.Service.Implementation;

import com.TuneWave.AudioApp.Entity.Audio;
import com.TuneWave.AudioApp.Repository.AudioRepository;
import com.TuneWave.AudioApp.Service.AudioService;
import com.TuneWave.AudioApp.Entity.User;
import com.TuneWave.AudioApp.Service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AudioServiceImpl implements AudioService {

    private final AudioRepository audioRepository;
    private final UserService userService;

    public AudioServiceImpl(AudioRepository audioRepository, UserService userService){
        this.audioRepository = audioRepository;
        this.userService = userService;
    }

    @Override
    public List<Audio> findAll() {
        return audioRepository.findAll();
    }

    @Override
    public boolean createAudio(Audio audio) {
        User user = userService.getUserById(audio.getUser().getId());
        if(user != null ){
            if(!user.isArtist()){
                user.setArtist(true);
            }
            userService.updateUser(user);
            audioRepository.save(audio);
            return true;
        }
        return false;
    }

    @Override
    public Audio getAudioById(Long id){
        return audioRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteAudio(Long id) {
        try{
            Optional<Audio> audioOptional = audioRepository.findById(id);
            if(audioOptional.isPresent()){
                Audio oAudio = audioOptional.get();
                oAudio.getUser().getAudio().remove(oAudio);
                userService.updateUser(oAudio.getUser());
            }
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
