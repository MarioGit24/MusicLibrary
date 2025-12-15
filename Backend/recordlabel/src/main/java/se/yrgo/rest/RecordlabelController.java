package se.yrgo.rest;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import se.yrgo.domain.*;
import se.yrgo.service.*;
import se.yrgo.single_ep.domain.*;

@RestController
@RequestMapping("/recordlabels")
@CrossOrigin(origins = "http://localhost:3000")
public class RecordlabelController {

    private final RecordlabelService recordlabelService;

    @Autowired
    public RecordlabelController(RecordlabelService recordlabelService) {
        this.recordlabelService = recordlabelService;
    }

    @GetMapping("/detailed")
    public List<RecordlabelResponseDTO> getAllRecordlabels() {
        return recordlabelService.getAllRecordlabels();
    }

    @PostMapping
    public ResponseEntity<String> createRecordlabel(@RequestBody Recordlabel recordlabel) {
        recordlabelService.createRecordlabel(recordlabel);
        return ResponseEntity.ok("Record label created succesfully!");
    }
}
