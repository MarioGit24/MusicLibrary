package se.yrgo.domain;

import java.util.*;

import jakarta.persistence.*;

@Entity
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Long artistId;
    private Long recordLabelId;

    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
    private List<Song> songs = new ArrayList<>();

    public Album() {
    }

    public void addSong(Song song) {
        if (songs == null) {
            songs = new ArrayList<>();
        }
        songs.add(song);
        song.setAlbum(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Album))
            return false;
        Album album = (Album) o;
        return id != null && id.equals(album.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Album{id=" + id + ", title='" + title + "'}";
    }
}