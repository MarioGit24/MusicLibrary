package se.yrgo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import se.yrgo.domain.Ep;
import se.yrgo.dto.EpCreationRequestDTO;
import se.yrgo.service.EpService;




@RestController
@RequestMapping("/eps")

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
        return epService.getAllEps(); 
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
        Ep createdEp = epService.createEp(requestDTO);
        return new ResponseEntity<>(createdEp, HttpStatus.CREATED);
    }





    

    
}
