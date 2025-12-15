package se.yrgo.service;

import java.util.List;

import se.yrgo.domain.Song;

public interface SongService {
    List<Song> getAllSongs();

    Song createSong(Song song);

    Song getSongById(Long id);

    Song updateSong(Long id, Song song);

    void deleteSong(Long id);
    
}
