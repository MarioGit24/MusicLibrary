package se.yrgo.service;

import java.util.*;

import se.yrgo.domain.*;

public interface ArtistService {

    public List<Artist> getAllArtists();

    public Artist getArtist(Long id);

    public Artist createArtist(Artist artist);
}
