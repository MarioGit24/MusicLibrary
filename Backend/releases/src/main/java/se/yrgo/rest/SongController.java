package se.yrgo.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import se.yrgo.domain.Song;
import se.yrgo.dto.SongDTO;
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
    public List<SongDTO> getAllSongs(){
       return songService.getAllSongs().stream()
           .map(song -> new SongDTO(song.getId(), song.getTitle(), song.getDuration()))
           .collect(Collectors.toList());
}
    @GetMapping("/{id}")
    public SongDTO getSongById(@PathVariable Long id){
        Song song = songService.getSongById(id);
        return new SongDTO(song.getId(), song.getTitle(), song.getDuration());
    }

    @PutMapping("/{id}") 
    public ResponseEntity<SongDTO> updateSong(@PathVariable Long id, @RequestBody SongDTO dto){
        Song songDetails = new Song(dto.getTitle(), dto.getDuration());
        
        Song updatedSong = songService.updateSong(id, songDetails);
        
        SongDTO response = new SongDTO(updatedSong.getId(), updatedSong.getTitle(), updatedSong.getDuration());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<SongDTO> createSong(@RequestBody SongDTO dto){ 
        Song songEntity = new Song(dto.getTitle(), dto.getDuration());
        Song newSong = songService.createSong(songEntity);
        
        SongDTO response = new SongDTO(newSong.getId(), newSong.getTitle(), newSong.getDuration());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")  
    public ResponseEntity<Void> deleteSong(@PathVariable Long id) {
        songService.deleteSong(id);
        return ResponseEntity.noContent().build();
    }
}
