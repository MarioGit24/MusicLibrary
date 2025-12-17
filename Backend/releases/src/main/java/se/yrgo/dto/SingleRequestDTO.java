package se.yrgo.dto;

public class SingleRequestDTO {
    private String title;
    private int duration;
    private Long recordlabelId;
    private Long artistId;

    public SingleRequestDTO(){}

    public SingleRequestDTO(String title, int duration, Long recordlabelId, Long artistId){
        this.title = title;
        this.duration = duration;
        this.recordlabelId = recordlabelId;
        this.artistId = artistId;
    }

    public String getTitle() {
        return title;
    }

    public Long getRecordlabelId() {
        return recordlabelId;
    }

    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public void setRecordlabelId(Long recordlabelId) {
        this.recordlabelId = recordlabelId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    


    
}
