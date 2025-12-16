package se.yrgo.rest;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import se.yrgo.dto.*;
import se.yrgo.service.*;

@RestController
@RequestMapping("/releases")
public class ReleaseController {
    private final ReleaseService releaseService;

    @Autowired
    public ReleaseController(ReleaseService releaseService) {
        this.releaseService = releaseService;
    }

    @GetMapping
    public List<ReleaseDTO> getAllReleasesForLabel(@RequestParam Long recordlabelId) {
        // should call a service that fetches both Albums and EPs
        // and combines them into a single list of ReleaseDTOs.
        return releaseService.findAllByRecordlabelId(recordlabelId);
    }
}
