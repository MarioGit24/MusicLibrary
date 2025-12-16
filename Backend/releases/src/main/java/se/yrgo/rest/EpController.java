package se.yrgo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import se.yrgo.domain.Album;
import se.yrgo.domain.Ep;
import se.yrgo.dto.AlbumCreationRequestDTO;
import se.yrgo.dto.EpCreationRequestDTO;
import se.yrgo.service.EpService;




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
    public List<Ep> getAllEps(@RequestParam(required = false) Long recordlabelId){
        if (recordlabelId != null){
            return epService.getEpsByRecordlabel(recordlabelId);
        }
        return epService.getEps(); 
    }

    @GetMapping("/{id}")
    public Ep GetEp(@PathVariable Long id){
        return epService.getEpById(id); 
    }

    @PutMapping("/{id}")
    public Ep updateEp(@PathVariable Long id, @RequestBody Ep ep) {
        return epService.updateEp(id, ep);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEp(@PathVariable Long id) {
        epService.deleteEp(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Ep> createEpWithSongs(@RequestBody EpCreationRequestDTO requestDTO) {
        // business logic??
        Ep createdEp = epService.createEp(requestDTO);
        return new ResponseEntity<>(createdEp, HttpStatus.CREATED);
    }





    

    
}
