package se.yrgo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import se.yrgo.domain.Song;
import se.yrgo.service.SongService;

@RequestController
@RequestMapping("songs")
@CrossOrigin(origins = "localhost:3000")
public class SongController {
    private SongService songService;


    @Autowired
    public SongController(SongService songService){
        this.songService = songService;
    }

    @GetMapping
    public List<Song>getAllSongs(){
        return songService.getAllSongs();
    }

    @PostMapping
    public ResponseEntity<Song> createSongs(@RequestBody Song song){
        Song newSong = songService.createSong(song);

        return new ResponseEntity<Song>(HttpStatus.CREATED);

    }
}
