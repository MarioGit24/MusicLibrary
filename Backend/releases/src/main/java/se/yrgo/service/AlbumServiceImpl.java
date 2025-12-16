package se.yrgo.service;

import java.util.*;

import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import se.yrgo.data.*;
import se.yrgo.domain.*;
import se.yrgo.dto.*;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;

    public AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }

    @Override
    @Transactional
    public Album createAlbum(AlbumCreationRequestDTO dto) {
        Album album = new Album();
        album.setTitle(dto.getTitle());
        album.setArtistId(dto.getArtistId());
        album.setRecordLabelId(dto.getRecordlabelId());

        if (dto.getSongsList() != null) {
            dto.getSongsList().forEach(songDto -> {
                Song song = new Song();
                song.setTitle(songDto.getTitle());
                song.setDuration(songDto.getDuration());

                album.addSong(song);
            });
        }

        return albumRepository.save(album);
    }

    @Override
    public Album getAlbumById(Long id) {
        return albumRepository.findById(id).orElseThrow(() -> new RuntimeException("Album was not found"));
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

    @Override
    public List<Album> getAlbumsByRecordlabel(Long recordlabelId) {
        return albumRepository.findByRecordlabelId(recordlabelId);
    }
}
