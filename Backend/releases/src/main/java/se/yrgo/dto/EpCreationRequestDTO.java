package se.yrgo.dto;

import java.util.*;

public class EpCreationRequestDTO{
    private String title;
    private Long artistId;
    private Long recordlabelId;
    private List<SongDTO> songs;

    public List<SongDTO> getSongs() {
        return songs;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public Long getRecordlabelId() {
        return recordlabelId;
    }

    public void setRecordlabelId(Long recordlabelId) {
        this.recordlabelId = recordlabelId;
    }

    public void setSongs(List<SongDTO> songsList) {
        this.songs = songsList;
    }

}