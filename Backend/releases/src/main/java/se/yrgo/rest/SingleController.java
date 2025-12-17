package se.yrgo.rest;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import se.yrgo.dto.*;
import se.yrgo.service.SingleService;



@RestController
@RequestMapping("/singles")
@CrossOrigin(origins = "http://localhost:3000")
public class SingleController {

    private final SingleService singleService;

    @Autowired
    public SingleController(SingleService singleService) {
        this.singleService = singleService;
    }

    @GetMapping
    public List<SingleResponseDTO> getAllSingles() {
        return singleService.getSingles(); 
    }

    @GetMapping("/{id}")
    public SingleResponseDTO getSingle(@PathVariable Long id) {
        return singleService.getSingleById(id);
    }

    @PostMapping
    public ResponseEntity<SingleResponseDTO> createSingle(@RequestBody SingleRequestDTO requestDTO) {
        SingleResponseDTO response = singleService.createSingle(requestDTO); 
        return new ResponseEntity<>(response, HttpStatus.CREATED); 
    }

    @PutMapping("/{id}")
    public ResponseEntity<SingleResponseDTO> updateSingle(@PathVariable Long id, @RequestBody SingleRequestDTO requestDTO) {
        SingleResponseDTO response = singleService.updateSingle(id, requestDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSingle(@PathVariable Long id) {
        singleService.deleteSingle(id);
        return ResponseEntity.noContent().build();
    }
}