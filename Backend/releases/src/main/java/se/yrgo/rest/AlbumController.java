package se.yrgo.rest;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import se.yrgo.dto.*;
import se.yrgo.service.*;

@RestController
@RequestMapping("/albums")
@CrossOrigin(origins = "http://localhost:3000")
public class AlbumController {

    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping
    public List<AlbumResponseDTO> getAllAlbums(@RequestParam(required = false) Long recordlabelId) {
        if (recordlabelId != null) {
            return albumService.getAlbumsByRecordlabel(recordlabelId);
        }
        return albumService.getAllAlbums();
    }


    @GetMapping("/{id}")
    public AlbumResponseDTO getAlbum(@PathVariable Long id) {
        return albumService.getAlbumById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlbumResponseDTO> updateAlbum(@PathVariable Long id,
            @RequestBody AlbumCreationRequestDTO requestDTO) {
        AlbumResponseDTO response = albumService.updateAlbum(id, requestDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Long id) {
        albumService.deleteAlbum(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<AlbumResponseDTO> createAlbumWithSongs(@RequestBody AlbumCreationRequestDTO requestDTO) {
        // business logic??
        AlbumResponseDTO response = albumService.createAlbum(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
