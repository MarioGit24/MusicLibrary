package se.yrgo.service;

import java.util.*;

import org.springframework.stereotype.*;

import se.yrgo.data.*;
import se.yrgo.domain.*;

@Service
public class ArtistServiceImpl implements ArtistService {
    private final ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    @Override
    public Artist createArtist(Artist artist) {
        return artistRepository.save(artist);
    }

    public Artist getArtist(Long id) {
        return artistRepository.findById(id).orElseThrow(() -> new RuntimeException("Artist not found"));
    }

    public Artist updateArtistLabel(Long artistId, Long labelId) {
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new RuntimeException("Artist not found"));

        artist.setLabelId(labelId);
        return artistRepository.save(artist);
    }
}
