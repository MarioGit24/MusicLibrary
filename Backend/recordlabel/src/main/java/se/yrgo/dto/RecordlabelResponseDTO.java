package se.yrgo.single_ep.domain;

public class RecordlabelResponseDTO {
    private Long recordlabelId;
    private String artistName;
    private String albumName;
    private String epName;
    private String singleName;
    private String songName;

    public RecordlabelResponseDTO(Long recordlabelId, String artistName, String albumName, String epName,
            String singleName, String songName) {
        this.recordlabelId = recordlabelId;
        this.artistName = artistName;
        this.albumName = albumName;
        this.epName = epName;
        this.singleName = singleName;
        this.songName = songName;
    }

    public Long getRecordlabelId() {
        return recordlabelId;
    }

    public void setRecordlabelId(Long recordlabelId) {
        this.recordlabelId = recordlabelId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getEpName() {
        return epName;
    }

    public void setEpName(String epName) {
        this.epName = epName;
    }

    public String getSingleName() {
        return singleName;
    }

    public void setSingleName(String singleName) {
        this.singleName = singleName;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

}
