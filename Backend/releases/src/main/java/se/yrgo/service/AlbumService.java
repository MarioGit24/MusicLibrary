package se.yrgo.service;

import java.util.*;

import se.yrgo.domain.*;
import se.yrgo.dto.*;

public interface AlbumService {

    List<Album> getAllAlbums();

    Album getAlbumById(Long id);

    Album updateAlbum(Long id, Album album);

    void deleteAlbum(Long id);

    Album createAlbum(AlbumCreationRequestDTO dto);

    List<Album> getAlbumsByRecordlabel(Long recordlabelId);
}
