package se.yrgo.dto;

import java.util.*;

public class ReleaseDTO {

    private Long id;
    private String title;
    private String type; // T.ex. "ALBUM", "SINGLE" eller "EP"
    private List<SongDTO> songs;

    public ReleaseDTO() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<SongDTO> getSongs() {
        return songs;
    }

    public void setSongs(List<SongDTO> songs) {
        this.songs = songs;
    }

}
