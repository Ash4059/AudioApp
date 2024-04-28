package com.TuneWave.AudioApp.Audio;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/audios")
public class AudioController {

    private final AudioService audioService;

    public AudioController(AudioService audioService) {
        this.audioService = audioService;
    }

    @GetMapping
    public ResponseEntity<List<Audio>> findAll() {
        return ResponseEntity.ok(audioService.findAll());
    }

    @PostMapping
    public ResponseEntity<String> addAudio(@RequestBody Audio audio) {
        audioService.createAudio(audio);
        return new ResponseEntity<String>("Audio added successfully!", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Audio> getAudioById(@PathVariable Long id) {
        Audio audio = audioService.getAudioById(id);
        return new ResponseEntity<Audio>(audio, audio != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAudio(@PathVariable Long id) {
        boolean deleted = audioService.deleteAudio(id);
        if (deleted) {
            return new ResponseEntity<String>("Audio deleted successfully!", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Audio not found", HttpStatus.NOT_FOUND);
    }

    @PutMapping
    public ResponseEntity<String> updateAudio(@RequestBody Audio audio) {
        boolean updated = audioService.updateAudio(audio);
        if (updated) {
            return new ResponseEntity<String>("Audio updated successfully!", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Audio not found", HttpStatus.NOT_FOUND);
    }
}
