package se.yrgo.domain;

import java.util.*;

import jakarta.persistence.*;

public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long album_Id;

    private String title;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        result = prime * result + ((title == null) ? 0 : title.hashCode());
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
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (albums == null) {
            if (other.albums != null)
                return false;
        } else if (!albums.equals(other.albums))
            return false;
        return true;
    }
}
