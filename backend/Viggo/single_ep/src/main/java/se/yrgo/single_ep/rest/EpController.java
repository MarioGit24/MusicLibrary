package se.yrgo.single_ep.rest;

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

import se.yrgo.single_ep.domain.Ep;
import se.yrgo.single_ep.service.EpService;




@RestController
@RequestMapping("/eps")
@CrossOrigin(origins = "http://localhost:3000")
public class EpController {

    private final EpService epService;

    @Autowired
    public EpController(EpService epService) {
        this.epService = epService;
    } 

    @GetMapping 
    public List<Ep> getAllEps(){
        return epService.getEps(); 
    }

    @PostMapping
    public ResponseEntity<Ep> createEp(@RequestBody Ep ep){
        Ep createdEp = epService.createEp(ep);
        return new ResponseEntity<>(createdEp, HttpStatus.CREATED);
    }




    

    
}
