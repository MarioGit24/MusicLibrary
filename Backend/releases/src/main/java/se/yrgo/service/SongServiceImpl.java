package se.yrgo.service;

import java.util.*;

import org.springframework.stereotype.*;

import se.yrgo.data.*;
import se.yrgo.domain.*;

@Service
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;

    public SongServiceImpl(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public Song createSong(Song song) {
        return songRepository.save(song);
    }

    @Override
    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    @Override
    public Song getSongById(Long id) {
        return songRepository.findById(id).orElseThrow(() -> new RuntimeException("Song was not found"));
    }

    @Override
    public Song updateSong(Long id, Song song) {
        Song existing = getSongById(id);
        existing.setTitle(song.getTitle());
        existing.setAlbum(song.getAlbum());
        return songRepository.save(existing);
    }

    @Override
    public void deleteSong(Long id) {
        songRepository.deleteById(id);
    }

}
