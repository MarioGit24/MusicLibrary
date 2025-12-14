package se.yrgo.service;

import java.util.List;

import se.yrgo.domain.Song;

public interface SongService {
    public List<Song> getAllSongs();

    public Song createSong(Song song);
    

    

    
}
