package se.yrgo.service;

import java.util.*;

import org.springframework.stereotype.Service;

import se.yrgo.data.AlbumRepository;
import se.yrgo.domain.Album;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;

    public AlbumServiceImpl(AlbumRepository albumRepository){
        this.albumRepository = albumRepository;
    }

    @Override
    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }
    

    @Override
    public Album createAlbum(Album album) {
        return albumRepository.save(album);
    }

    @Override
    public Album getAlbumById(Long id){
        return albumRepository.findById(id).
        orElseThrow(() -> new RuntimeException("Album was not found"));
    }

    @Override
    public Album updateAlbum(Long id, Album album) {
    Album existing = getAlbumById(id);
    existing.setTitle(album.getTitle());
    existing.setSongs(album.getSongs());
    return albumRepository.save(existing);
    }   

    @Override
    public void deleteAlbum(Long id) {
    albumRepository.deleteById(id);
    }
    }
