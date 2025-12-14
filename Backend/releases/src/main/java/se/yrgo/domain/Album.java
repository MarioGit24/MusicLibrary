package se.yrgo.domain;

import java.util.*;

import jakarta.persistence.*;

public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long album_Id;

    private String album;

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL);
    private List<Album> albums = new ArrayList<>();

    public Album() {
    }

    public long getAlbum_Id() {
        return album_Id;
    }

    public void setAlbum_Id(long album_Id) {
        this.album_Id = album_Id;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (album_Id ^ (album_Id >>> 32));
        result = prime * result + ((album == null) ? 0 : album.hashCode());
        result = prime * result + ((albums == null) ? 0 : albums.hashCode());
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
        Album other = (Album) obj;
        if (album_Id != other.album_Id)
            return false;
        if (album == null) {
            if (other.album != null)
                return false;
        } else if (!album.equals(other.album))
            return false;
        if (albums == null) {
            if (other.albums != null)
                return false;
        } else if (!albums.equals(other.albums))
            return false;
        return true;
    }
}
