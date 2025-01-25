package com.TuneWave.AudioApp.Controller;

import java.io.File;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.TuneWave.AudioApp.Firebase.Res;
import com.TuneWave.AudioApp.Service.Implementation.DriveService;

@RestController
public class SongController {

    private final DriveService driveService;

    public SongController(DriveService driveService) {
        this.driveService = driveService;
    }
    
    @PostMapping("/image")
    public ResponseEntity<?> addSong(@RequestParam("image") MultipartFile image) {
        if(image.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        try {
            File tempFile = File.createTempFile("temp", null);
            image.transferTo(tempFile);
            Res res = driveService.uploadFileToDrive(tempFile);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
