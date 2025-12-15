package se.yrgo.service;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import se.yrgo.data.*;
import se.yrgo.domain.*;
import se.yrgo.single_ep.domain.*;

@Service
public class RecordlabelServiceImpl implements RecordlabelService {

    private final RecordlabelRepository recordlabelRepository;
    private final RestClient restClient;

    @Value("${artist.service.url}")
    private String artistServiceUrl;

    @Value("${releases.service.url}")
    private String releasesServiceUrl;

    public RecordlabelServiceImpl(RecordlabelRepository recordlabelRepository, RestClient restClient) {
        this.recordlabelRepository = recordlabelRepository;
        this.restClient = restClient;
    }

    @Override
    public RecordlabelResponseDTO getRecordlabelDetails(Long labelId) {
        Recordlabel label = recordlabelRepository.findById(labelId)
                .orElseThrow(() -> new ResourceNotFoundException("Label not found"));

        List<ArtistDTO> artists = restClient.get()
                .uri(artistServiceUrl + "?labelId=" + labelId)
                .retrieve()
                .body(new ParameterizedTypeReference<List<ArtistDTO>>() {
                });

        List<ReleaseDTO> releases = restClient.get()
                .uri(releasesServiceUrl + "?labelId=" + labelId)
                .retrieve()
                .body(new ParameterizedTypeReference<List<ReleaseDTO>>() {
                });

        return new RecordlabelResponseDTO(label, artists, releases);
    }
}