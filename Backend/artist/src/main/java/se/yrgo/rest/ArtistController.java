package se.yrgo.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import se.yrgo.dto.ArtistRequestDTO;
import se.yrgo.dto.ArtistResponseDTO;
import se.yrgo.service.ArtistService;

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
    public List<ArtistResponseDTO> getAllArtists(@RequestParam(required = false, name = "labelId") Long recordlabelId) {
        if (recordlabelId != null) {
            return artistService.findByRecordlabelId(recordlabelId);
        }
        return artistService.getAllArtists();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistResponseDTO> getArtistById(@PathVariable Long id) {
        return ResponseEntity.ok(artistService.getArtist(id));
    }

    @PostMapping
    public ResponseEntity<ArtistResponseDTO> createArtist(@RequestBody ArtistRequestDTO artistDto) {
        ArtistResponseDTO createdArtist = artistService.createArtist(artistDto);
        return new ResponseEntity<>(createdArtist, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/assign-label")
    public ResponseEntity<ArtistResponseDTO> assignToLabel(@PathVariable Long id, @RequestParam(name = "recordlabelId") Long recordlabelId) {
        ArtistResponseDTO updatedArtist = artistService.updateArtistLabel(id, recordlabelId);
        return ResponseEntity.ok(updatedArtist);
    }
}