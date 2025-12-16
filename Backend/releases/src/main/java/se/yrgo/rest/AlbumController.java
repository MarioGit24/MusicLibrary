package se.yrgo.rest;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import se.yrgo.domain.*;
import se.yrgo.dto.*;
import se.yrgo.service.*;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping
    public List<Album> getAllAlbums(@RequestParam(required = false) Long recordlabelId) {
        if (recordlabelId != null) {
            return albumService.getAlbumsByRecordlabel(recordlabelId);
        }
        return albumService.getAllAlbums();
    }

    // @PostMapping
    // public ResponseEntity<Album> createAlbum(@RequestBody Album album) {
    // Album createdAlbum = albumService.createAlbum(album);
    // return new ResponseEntity<>(createdAlbum, HttpStatus.CREATED);
    // }

    @GetMapping("/{id}")
    public Album getAlbum(@PathVariable Long id) {
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

    @PostMapping
    public ResponseEntity<Album> createAlbumWithSongs(@RequestBody AlbumCreationRequestDTO requestDTO) {
        // business logic??
        Album createdAlbum = albumService.createAlbum(requestDTO);
        return new ResponseEntity<>(createdAlbum, HttpStatus.CREATED);
    }

}
