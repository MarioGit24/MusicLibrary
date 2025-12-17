package se.yrgo.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import se.yrgo.data.ArtistRepository;
import se.yrgo.domain.Artist;
import se.yrgo.dto.*;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public List<ArtistResponseDTO> getAllArtists() {
        return artistRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ArtistResponseDTO getArtist(Long id) {
        Artist artist = artistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artist not found"));
        return convertToResponseDTO(artist);
    }

    @Override
    public ArtistResponseDTO createArtist(ArtistRequestDTO artistDto) {
        Artist artist = new Artist();
        artist.setName(artistDto.getName());
        artist.setRecordabelId(artistDto.getRecordLabelId());
        
        Artist savedArtist = artistRepository.save(artist);
        return convertToResponseDTO(savedArtist);
    }

    @Override
    public ArtistResponseDTO updateArtistLabel(Long artistId, Long recordlabelId) {
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new RuntimeException("Artist not found"));

        artist.setRecordabelId(recordlabelId);
        Artist updatedArtist = artistRepository.save(artist);
        return convertToResponseDTO(updatedArtist);
    }

    @Override
    public List<ArtistResponseDTO> findByRecordlabelId(Long recordlabelId) {
        return artistRepository.findByRecordlabelId(recordlabelId).stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    private ArtistResponseDTO convertToResponseDTO(Artist artist) {
        ArtistResponseDTO dto = new ArtistResponseDTO();
        dto.setId(artist.getId());
        dto.setName(artist.getName());
        dto.setRecordLabelId(artist.getRecordlabelId());
        return dto;
    }
}