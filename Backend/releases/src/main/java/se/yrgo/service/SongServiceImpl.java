package se.yrgo.service;

import java.util.*;

import org.springframework.stereotype.Service;

import se.yrgo.data.SongRepository;
import se.yrgo.domain.Song;

@Service
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;

    public SongServiceImpl(SongRepository songRepository){
        this.songRepository = songRepository;
    }
    
    @Override
    public Song createSong(Song song){
        return songRepository.save(song);
    }

    @Override
    public List<Song> getAllSongs(){
     List<Song> songs = new ArrayList<>();
     songs = songRepository.findAll();
     return songs;
    }
}
