package se.yrgo.dto;

public class SingleResponseDTO {
    private String title;
    private int duration;
    private Long recordlabelId;

    public SingleResponseDTO(){}

    public SingleResponseDTO(String title, int duration, Long recordlabelId){
        this.title = title;
        this.duration = duration;
        this.recordlabelId = recordlabelId;
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
