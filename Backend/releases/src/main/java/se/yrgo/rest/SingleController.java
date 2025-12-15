package se.yrgo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import se.yrgo.domain.Single;
import se.yrgo.service.SingleService;



@RestController
@RequestMapping("/singles")
@CrossOrigin(origins = "http://localhost:3000")

public class SingleController {

    private SingleService singleService;

    @Autowired
    public SingleController(SingleService singleService) {
        this.singleService = singleService;
    }

    @GetMapping
    public List<Single> getAllSingles(){
        return singleService.getSingles(); 
    }

    @PostMapping
    public ResponseEntity<Single> createSingle(@RequestBody Single single){
        Single newSingle = singleService.createSingle(single); 
        return new ResponseEntity<>(newSingle, HttpStatus.CREATED); 
    }

    @GetMapping("/{id}")
    public Single getSingle(@PathVariable Long id){
        return singleService.getSingleById(id);
    }

    @PutMapping("/{id}")
    public Single updateSingle(@PathVariable Long id, @RequestBody Single single) {
        return singleService.updateSingle(id, single);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSingle(@PathVariable Long id) {
        singleService.deleteSingle(id);
        return ResponseEntity.noContent().build();
    }
}
