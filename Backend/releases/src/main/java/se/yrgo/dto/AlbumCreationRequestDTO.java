package se.yrgo.dto;

import java.util.*;

public class AlbumCreationRequestDTO {
    private String title;
    private Long artistId;
    private Long labelId;
    private List<SongCreationDTO> songsList;

    public List<SongCreationDTO> getSongsList() {
        return songsList;
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

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }

    public void setSongsList(List<SongCreationDTO> songsList) {
        this.songsList = songsList;
    }

}