package se.yrgo.service;

import java.util.*;
import se.yrgo.dto.*;

public interface ArtistService {

    List<ArtistResponseDTO> getAllArtists();

    ArtistResponseDTO getArtist(Long id);

    ArtistResponseDTO createArtist(ArtistRequestDTO artistDto);

    ArtistResponseDTO updateArtistLabel(Long artistId, Long recordlabelId);

    List<ArtistResponseDTO> findByRecordlabelId(Long recordlabelId);
}