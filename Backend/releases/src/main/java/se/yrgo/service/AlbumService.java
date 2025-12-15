package se.yrgo.service;

import java.util.List;

import se.yrgo.domain.Album;
import se.yrgo.dto.*;

public interface AlbumService {

    List<Album> getAllAlbums();

    Album getAlbumById(Long id);

    Album updateAlbum(Long id, Album album);

    void deleteAlbum(Long id);

    Album createAlbum(AlbumCreationRequestDTO dto);

}
