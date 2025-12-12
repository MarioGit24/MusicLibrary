package se.yrgo.single_ep.domain;

import java.util.*;

import jakarta.persistence.*;

public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long song_id;

    private String song;

    @ManyToOne(fetch = FetchType.LAZY)
    private List<Song> songs = new ArrayList<>();

    public Song() {
    }

    public long getSong_id() {
        return song_id;
    }

    public void setSong_id(long song_id) {
        this.song_id = song_id;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (song_id ^ (song_id >>> 32));
        result = prime * result + ((song == null) ? 0 : song.hashCode());
        result = prime * result + ((songs == null) ? 0 : songs.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Song other = (Song) obj;
        if (song_id != other.song_id)
            return false;
        if (song == null) {
            if (other.song != null)
                return false;
        } else if (!song.equals(other.song))
            return false;
        if (songs == null) {
            if (other.songs != null)
                return false;
        } else if (!songs.equals(other.songs))
            return false;
        return true;
    }
}
