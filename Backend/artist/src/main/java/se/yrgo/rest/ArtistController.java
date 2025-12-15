package se.yrgo.rest;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import se.yrgo.domain.*;
import se.yrgo.service.*;

@RestController
@RequestMapping("/artists")
@CrossOrigin(origins = "http://localhost:3000")
public class ArtistController {
    private final ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping
    public List<Artist> getAllArtists() {
        return artistService.getAllArtists();
    }

    @GetMapping("/{id}")
    public Artist getArtistById(@PathVariable Long id) {
        return artistService.getArtist(id);
    }

    @PostMapping
    public ResponseEntity<Artist> createArtist(@RequestBody Artist artist) {
        Artist createdArtist = artistService.createArtist(artist);
        return new ResponseEntity<>(createdArtist, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/assign-label")
    public ResponseEntity<Artist> assignToLabel(@PathVariable Long id, @RequestParam Long labelId) {
        Artist updatedArtist = artistService.updateArtistLabel(id, labelId);
        return ResponseEntity.ok(updatedArtist);
    }

}
