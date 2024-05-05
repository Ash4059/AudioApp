package com.TuneWave.AudioApp.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    private final ArtistService artistService;

    public ArtistController(ArtistService artistService){
        this.artistService = artistService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllArtist(){
        return new ResponseEntity<>(artistService.getAllArtist(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addArtist(@RequestBody User user){
         artistService.addArtist(user);
         return new ResponseEntity<>("Artist added successfully!", HttpStatus.OK);
    }

    @GetMapping("/{Id}")
    public ResponseEntity<User> findArtistBId(@PathVariable Long Id){
        User user = artistService.getArtistById(Id);
        if(user == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> updateArtist(@RequestBody User user){
        if(artistService.updateArtist(user)){
            return new ResponseEntity<>("Artist updated successfully!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Artist not found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<String> deleteArtist(@PathVariable Long Id){
        if(artistService.deleteArtist(Id)){
            return new ResponseEntity<>("Artist deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Artist not found", HttpStatus.NOT_FOUND);
    }
}
