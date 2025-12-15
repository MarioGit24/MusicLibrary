package se.yrgo.service;

import java.util.List;

import se.yrgo.domain.Album;

public interface AlbumService {

    List<Album> getAllAlbums();
    
    Album createAlbum(Album album);

    Album getAlbumById(Long id);

    Album updateAlbum(Long id, Album album);

    void deleteAlbum(Long id);

}
