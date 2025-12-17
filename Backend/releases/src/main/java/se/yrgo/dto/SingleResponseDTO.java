package se.yrgo.dto;

public class SingleResponseDTO {
    private String title;
    private int duration;
    private Long recordlabelId;
    private Long artistId;

    public SingleResponseDTO(){}

    public SingleResponseDTO(String title, int duration, Long recordlabelId, Long artistId){
        this.title = title;
        this.duration = duration;
        this.recordlabelId = recordlabelId;
        this.artistId = artistId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Long getRecordlabelId() {
        return recordlabelId;
    }

    public void setRecordlabelId(Long recordlabelId) {
        this.recordlabelId = recordlabelId;
    }

    

    
}
