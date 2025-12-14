package se.yrgo.service;

import java.util.List;

import se.yrgo.domain.Album;

public interface AlbumService {

    public List<Album> getAllAlbums();
    
    public Album createAlbum(Album album);

}
