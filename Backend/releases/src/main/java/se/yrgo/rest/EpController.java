package se.yrgo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import se.yrgo.domain.Ep;
import se.yrgo.dto.EpCreationRequestDTO;
import se.yrgo.dto.EpResponseDTO;
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
    public List<EpResponseDTO> getAllEps(@RequestParam(required = false) Long recordlabelId){
        if (recordlabelId != null){
            return epService.getEpsByRecordlabel(recordlabelId);
        }
        return epService.getAllEps(); 
    }

    @GetMapping("/{id}")
    public EpResponseDTO GetEp(@PathVariable Long id){
        return epService.getEpById(id); 
    }

    @PutMapping("/{id}")
    public ResponseEntity<EpResponseDTO> updateEp(@PathVariable Long id, @RequestBody EpCreationRequestDTO requestDTO) {
        EpResponseDTO response = epService.updateEp(id, requestDTO); 
        return ResponseEntity.ok(response); 
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEp(@PathVariable Long id) {
        epService.deleteEp(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<EpResponseDTO> createEpWithSongs(@RequestBody EpCreationRequestDTO requestDTO) {
        EpResponseDTO createdEp = epService.createEp(requestDTO);
        return new ResponseEntity<>(createdEp, HttpStatus.CREATED);
    }





    

    
}
