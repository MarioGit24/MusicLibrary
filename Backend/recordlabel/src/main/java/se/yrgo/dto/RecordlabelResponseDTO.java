package se.yrgo.dto;

import java.util.*;

import se.yrgo.domain.*;

public class RecordlabelResponseDTO {
    private Long id;
    private String labelName;
    private List<ArtistDTO> artists;
    private List<ReleaseDTO> releases;

    public RecordlabelResponseDTO(Recordlabel label, List<ArtistDTO> artists, List<ReleaseDTO> releases) {
        this.id = label.getId();
        this.labelName = label.getName();
        this.artists = artists;
        this.releases = releases;
    }

    public Long getId() {
        return id;
    }

    public String getLabelName() {
        return labelName;
    }

    public List<ArtistDTO> getArtists() {
        return artists;
    }

    public List<ReleaseDTO> getReleases() {
        return releases;
    }

}
