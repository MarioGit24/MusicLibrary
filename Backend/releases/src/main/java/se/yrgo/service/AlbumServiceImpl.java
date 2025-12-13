package se.yrgo.service;

import java.util.*;

import se.yrgo.data.AlbumRepository;
import se.yrgo.domain.Album;

public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;

    public AlbumServiceImpl(AlbumRepository albumRepository){
        this.albumRepository = albumRepository;
    }

    @Override
    public List<Album> getAllAlbums() {
        List<Album> albums = new ArrayList<>();
        albums = albumRepository.findAll();
        return albums;
    }

    @Override
    public Album createAlbum(Album album) {
        return albumRepository.save(album);
    }
}
