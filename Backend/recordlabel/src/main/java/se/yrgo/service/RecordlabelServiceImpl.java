package se.yrgo.service;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.core.*;
import org.springframework.stereotype.*;
import org.springframework.web.client.*;

import se.yrgo.data.*;
import se.yrgo.domain.*;
import se.yrgo.dto.*;

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
    public RecordlabelResponseDTO createRecordlabel(RecordlabelRequestDTO dto) {
        Recordlabel label = new Recordlabel();
        label.setName(dto.getLabelName());

        Recordlabel savedLabel = recordlabelRepository.save(label);

        return new RecordlabelResponseDTO(savedLabel, new ArrayList<>(), new ArrayList<>());
    }

    @Override
    public RecordlabelResponseDTO getRecordlabelDetails(Long recordlabelId) {
        Recordlabel label = recordlabelRepository.findById(recordlabelId)
                .orElseThrow(() -> new RuntimeException("Label not found"));

        List<ArtistDTO> artists = restClient.get()
                .uri(artistServiceUrl + "?labelId=" + recordlabelId)
                .retrieve()
                .body(new ParameterizedTypeReference<List<ArtistDTO>>() {
                });

        List<ReleaseDTO> releases = restClient.get()
                .uri(releasesServiceUrl + "?recordlabelId=" + recordlabelId)
                .retrieve()
                .body(new ParameterizedTypeReference<List<ReleaseDTO>>() {
                });

        return new RecordlabelResponseDTO(label, artists, releases);
    }

    @Override
    public boolean enrollArtist(Long artistId, Long recordlabelId) {
        try {
            restClient.put()
                    .uri(artistServiceUrl + "/" + artistId + "/assign-label?recordlabelId=" + recordlabelId)
                    .retrieve()
                    .toBodilessEntity();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}