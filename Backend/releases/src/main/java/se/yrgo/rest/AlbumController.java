package se.yrgo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import se.yrgo.domain.Album;
import se.yrgo.service.AlbumService;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService){
        this.albumService = albumService;
    }

    @GetMapping
    public List<Album> getAllAlbums(){
        return albumService.getAllAlbums();
    }

    @PostMapping
    public ResponseEntity<Album> createAlbum(@RequestBody Album album){
        Album createdAlbum = albumService.createAlbum(album);
        return new ResponseEntity<>(createdAlbum,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Album getAlbum(@PathVariable Long id){
        return albumService.getAlbumById(id);
    }
    
    @PutMapping("/{id}")
    public Album updateAlbum(@PathVariable Long id, @RequestBody Album album) {
        return albumService.updateAlbum(id, album);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Long id) {
        albumService.deleteAlbum(id);
        return ResponseEntity.noContent().build();
    }




}
