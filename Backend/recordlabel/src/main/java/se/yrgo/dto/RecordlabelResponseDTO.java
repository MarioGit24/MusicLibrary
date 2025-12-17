package se.yrgo.dto;

import java.util.List;

import se.yrgo.domain.Recordlabel;

public class RecordlabelResponseDTO {
    private Long id;
    private String labelName;
    private List<ArtistDTO> artists;
    private List<ReleaseDTO> releases;

    public RecordlabelResponseDTO() {}

    public RecordlabelResponseDTO(Recordlabel label, List<ArtistDTO> artists, List<ReleaseDTO> releases) {
        this.id = label.getId();
        this.labelName = label.getName();
        this.artists = artists;
        this.releases = releases;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public List<ArtistDTO> getArtists() {
        return artists;
    }

    public void setArtists(List<ArtistDTO> artists) {
        this.artists = artists;
    }

    public List<ReleaseDTO> getReleases() {
        return releases;
    }

    public void setReleases(List<ReleaseDTO> releases) {
        this.releases = releases;
    }

}