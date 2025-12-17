package se.yrgo.service;

import java.util.*;

import se.yrgo.dto.*;

public interface AlbumService {

    List<AlbumResponseDTO> getAllAlbums();

    AlbumResponseDTO getAlbumById(Long id);

    AlbumResponseDTO updateAlbum(Long id, AlbumCreationRequestDTO album);

    void deleteAlbum(Long id);

    AlbumResponseDTO createAlbum(AlbumCreationRequestDTO dto);

    List<AlbumResponseDTO> getAlbumsByRecordlabel(Long recordlabelId);
}
