package se.yrgo.rest;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import se.yrgo.domain.*;
import se.yrgo.service.*;

@RestController
@RequestMapping("/recordlabels")
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

    @PostMapping("/create-recordlabel")
    public ResponseEntity<String> createRecordlabel(@RequestBody Recordlabel recordlabel) {
        recordlabelService.createRecordlabel(recordlabel);
        return ResponseEntity.ok("Record label created succesfully!");
    }

}
