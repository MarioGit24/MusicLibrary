package se.yrgo.single_ep.rest;

import java.net.http.HttpClient;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import se.yrgo.single_ep.domain.Single;
import se.yrgo.single_ep.service.SingleService;



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




}
