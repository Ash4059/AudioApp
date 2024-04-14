package com.TuneWave.AudioApp.Audio;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Audio>> findAll(){
        return ResponseEntity.ok(audioService.findAll());
    }

    @PostMapping("/audio")
    public ResponseEntity<String> addAudio(@RequestBody Audio audio){
        audioService.createAudio(audio);
        return new ResponseEntity<String>("Audio added successfully!",HttpStatus.OK);
    }

    @GetMapping("/audio/{id}")
    public ResponseEntity<Audio> getAudioById(@PathVariable Long id){
        Audio audio = audioService.getAudioById(id);
        return new ResponseEntity<Audio>(audio, audio != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/audio/{id}")
    public ResponseEntity<String> deleteAudio(@PathVariable Long id){
        boolean deleted = audioService.deleteAudio(id);
        return new ResponseEntity<String>("Audio deleted successfully!", deleted ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
