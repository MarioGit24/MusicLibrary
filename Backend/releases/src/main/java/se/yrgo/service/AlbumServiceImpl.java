package se.yrgo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import se.yrgo.data.AlbumRepository;
import se.yrgo.domain.*;
import se.yrgo.dto.*;


@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    
    public AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public List<AlbumResponseDTO> getAllAlbums() {
        return albumRepository.findAll().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    @Override
    public AlbumResponseDTO createAlbum(AlbumCreationRequestDTO dto) {
        Album album = new Album();
        album.setTitle(dto.getTitle());
        album.setArtistId(dto.getArtistId());
        album.setRecordlabelId(dto.getRecordlabelId());

        if (dto.getSongsList() != null) {
            for (SongDTO sDto : dto.getSongsList()) {
                Song song = new Song(sDto.getTitle(), sDto.getDuration());
                song.setAlbum(album); 
                album.getSongs().add(song);
            }
        }

        Album savedAlbum = albumRepository.save(album);
        return convertToDTO(savedAlbum); 
    }

    @Override
    public AlbumResponseDTO getAlbumById(Long id) {
        Album album = albumRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Album was not found"));
        return convertToDTO(album); 
    }

    @Override
    public AlbumResponseDTO updateAlbum(Long id, AlbumCreationRequestDTO dto) {
        Album existing = albumRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Album not found"));
            
        existing.setTitle(dto.getTitle());
        existing.setArtistId(dto.getArtistId());
        existing.setRecordlabelId(dto.getRecordlabelId());
        
        Album savedAlbum = albumRepository.save(existing);
        return convertToDTO(savedAlbum);
    }

    @Override
    public void deleteAlbum(Long id) {
        albumRepository.deleteById(id);
    }

    @Override
    public List<AlbumResponseDTO> getAlbumsByRecordlabel(Long recordlabelId) {
        return albumRepository.findByRecordlabelId(recordlabelId).stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList()); 
    }

    private AlbumResponseDTO convertToDTO(Album album) {
        AlbumResponseDTO response = new AlbumResponseDTO();
        response.setId(album.getId());
        response.setTitle(album.getTitle());
        response.setArtistId(album.getArtistId());
        response.setRecordlabelId(album.getRecordlabelId());

        if (album.getSongs() != null) {
            List<SongDTO> songDTOs = album.getSongs().stream()
                .map(s -> new SongDTO(s.getId(), s.getTitle(), s.getDuration()))
                .collect(Collectors.toList());
            response.setSongs(songDTOs);
        }
        return response;
    }
}
