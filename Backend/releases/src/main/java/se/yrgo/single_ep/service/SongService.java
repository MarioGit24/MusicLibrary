package se.service;

import java.util.List;

import org.springframework.stereotype.Service;

import se.domain.Song;

@Service
public class SongService {
    List<Song> getAllSongs(){}

    public Song createSong(Song song){
        return song;
    }

    

    
}
