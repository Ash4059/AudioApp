package com.TuneWave.AudioApp.Artist;

import jakarta.websocket.server.PathParam;
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
    public ResponseEntity<List<Artist>> getAllArtist(){
        return new ResponseEntity<>(artistService.getAllArtist(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addArtist(@RequestBody Artist artist){
         artistService.addArtist(artist);
         return new ResponseEntity<>("Artist added successfully!", HttpStatus.OK);
    }

    @GetMapping("/{Id}")
    public ResponseEntity<Artist> findArtistBId(@PathVariable Long Id){
        Artist artist = artistService.getArtistById(Id);
        if(artist == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(artist, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> updateArtist(@RequestBody Artist artist){
        if(artistService.updateArtist(artist)){
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
