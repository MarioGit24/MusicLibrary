package se.yrgo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import se.yrgo.domain.Song;
import se.yrgo.service.SongService;

@RestController
@RequestMapping("/songs")
@CrossOrigin(origins = "http://localhost:3000")
public class SongController {

    private final SongService songService;

    @Autowired
    public SongController(SongService songService){
        this.songService = songService;
    }

    @GetMapping
    public List<Song> getAllSongs(){
        return songService.getAllSongs();
    }

    @GetMapping("/{id}")
    public Song getSongById(@PathVariable Long id){
        return songService.getSongById(id);
    }

    @PutMapping("/{id}") 
    public Song updateSong(@PathVariable Long id, @RequestBody Song song){
        return songService.updateSong(id, song);
    }

    @PostMapping
    public ResponseEntity<Song> createSong(@RequestBody Song song){
        Song newSong = songService.createSong(song);
        return new ResponseEntity<>(newSong, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")  
    public ResponseEntity<Void> deleteSong(@PathVariable Long id) {
        songService.deleteSong(id);
        return ResponseEntity.noContent().build();
    }
}
