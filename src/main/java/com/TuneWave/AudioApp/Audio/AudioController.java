package com.TuneWave.AudioApp.Audio;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AudioController {

    private final AudioService audioService;

    public AudioController(AudioService audioService){
        this.audioService = audioService;
    }

    @GetMapping("/audios")
    public List<Audio> findAll(){
        return audioService.findAll();
    }

    @PostMapping("/audio")
    public String addAudio(@RequestBody Audio audio){
        audioService.createAudio(audio);
        return "Audio added successfully!";
    }

    @GetMapping("/audio/{id}")
    public Audio getAudioById(@PathVariable Long id){
        return audioService.getAudioById(id);
    }
}
