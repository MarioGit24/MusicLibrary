package se.yrgo.rest;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import se.yrgo.dto.*;
import se.yrgo.service.*;

@RestController
@RequestMapping("/recordlabels")
@CrossOrigin(origins = "http://localhost:3000")
public class RecordlabelController {
    private final RecordlabelService recordlabelService;

    @Autowired
    public RecordlabelController(RecordlabelService recordlabelService) {
        this.recordlabelService = recordlabelService;
    }

    @PostMapping
    public ResponseEntity<RecordlabelResponseDTO> createLabel(@RequestBody RecordlabelRequestDTO requestDTO) {
        RecordlabelResponseDTO newLabel = recordlabelService.createRecordlabel(requestDTO);
        return new ResponseEntity<>(newLabel, HttpStatus.CREATED);
    }

    @PutMapping("/{recordlabelId}/enroll-artist/{artistId}")
    public ResponseEntity<String> enrollArtist(@PathVariable Long recordlabelId, @PathVariable Long artistId) {
        boolean success = recordlabelService.enrollArtist(artistId, recordlabelId);
        if (success) {
            return ResponseEntity.ok("Artist " + artistId + " enrolled in Label " + recordlabelId);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to enroll artist");
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecordlabelResponseDTO> getLabelById(@PathVariable Long id) {
        RecordlabelResponseDTO labelDetails = recordlabelService.getRecordlabelDetails(id);
        return ResponseEntity.ok(labelDetails);
    }
    // @GetMapping("/{id}")
    // public Album getAlbum(@PathVariable Long id) {
    // return albumService.getAlbumById(id);
    // }
}
