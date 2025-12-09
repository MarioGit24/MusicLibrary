package se.yrgo.rest;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import se.yrgo.domain.*;
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

    @GetMapping
    public List<Recordlabel> getAllRecordlabels() {
        return recordlabelService.getAllRecordlabels();
    }

    @PostMapping
    public ResponseEntity<String> createRecordlabel(@RequestBody Recordlabel recordlabel) {
        recordlabelService.createRecordlabel(recordlabel);
        return ResponseEntity.ok("Record label created succesfully!");
    }

    // assign artist to record label
    @PostMapping("/enrollArtist")
    public ResponseEntity<String> enrollArtistInRecordlabel(
            @RequestParam Long artistId,
            @RequestParam Long recordlabelId) {

        boolean success = recordlabelService.enrollArtist(artistId, recordlabelId);

        if (!success) {
            return ResponseEntity.badRequest()
                    .body("Invalid recordlabel or artist ID provided.");
        }

        return ResponseEntity.ok(
                "Enrollment successful: Artist ID " + artistId +
                        " linked to Recordlabel ID " + recordlabelId);
    }

}
