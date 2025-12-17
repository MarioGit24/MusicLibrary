package se.yrgo.dto;

public class SingleResponseDTO {

    private Long id;
    private String title;
    private int duration;
    private Long artistId;
    private Long recordlabelId;

    public SingleResponseDTO(){}

    public SingleResponseDTO(Long id, String title, int duration, Long recordlabelId,long artistId){
        this.id=id;
        this.title = title;
        this.duration = duration;
        this.recordlabelId = recordlabelId;
        this.artistId=artistId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public int getDuration() {
        return duration;
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
