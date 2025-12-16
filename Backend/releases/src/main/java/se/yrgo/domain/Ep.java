package se.yrgo.domain;

import java.util.*;

import jakarta.persistence.*;

@Entity
public class Ep {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private Long artistId; 
    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    private Long recordLabelId;
    public Long getRecordLabelId() {
        return recordLabelId;
    }

    public void setRecordLabelId(Long recordLabelId) {
        this.recordLabelId = recordLabelId;
    }

    private String title;
    @OneToMany(mappedBy = "ep", cascade = CascadeType.ALL)
    private List<Song> songs = new ArrayList<>();

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
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
        Ep other = (Ep) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        return true;
    }

    public void addSong(Song song) {
        if (songs == null) {
            songs = new ArrayList<>();
        }
        songs.add(song);
        song.setEp(this);
    }

}
