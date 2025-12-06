package se.yrgo.domain;

import java.util.*;

import jakarta.persistence.*;

@Entity
public class Recordlabel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long record_id;

    private String name;

    @OneToMany(mappedBy = "recordlabel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Artist> artists = new ArrayList<>();

    public Recordlabel() {
    }

    public Recordlabel(Long record_id, String name) {
        this.record_id = record_id;
        this.name = name;
    }

    public Long getRecord_id() {
        return record_id;
    }

    public void setRecord_id(Long record_id) {
        this.record_id = record_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((record_id == null) ? 0 : record_id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((artists == null) ? 0 : artists.hashCode());
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
        Recordlabel other = (Recordlabel) obj;
        if (record_id == null) {
            if (other.record_id != null)
                return false;
        } else if (!record_id.equals(other.record_id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (artists == null) {
            if (other.artists != null)
                return false;
        } else if (!artists.equals(other.artists))
            return false;
        return true;
    }

    // @OneToMany(mappedBy = "ep", cascade = CascadeType.ALL, orphanRemoval = true)
    // private List<Ep> vehicles = new ArrayList<>();

    // @OneToMany(mappedBy = "single", cascade = CascadeType.ALL, orphanRemoval =
    // true)
    // private List<Signle> vehicles = new ArrayList<>();

    // @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval =
    // true)
    // private List<Album> vehicles = new ArrayList<>();

}