package se.yrgo.service;

import java.util.*;

import se.yrgo.domain.*;

public interface ArtistService {
    List<Artist> getAllArtists();

    public Artist createArtist(Artist artist);
}
