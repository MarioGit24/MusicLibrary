package se.yrgo.dto;

public class SingleRequestDTO {
    private String title;
    private int duration;
    private Long recordlabelId;

    public SingleRequestDTO(){}

    public SingleRequestDTO(String title, int duration, Long recordlabelId){
        this.title = title;
        this.duration = duration;
        this.recordlabelId = recordlabelId;
    }

    public String getTitle() {
        return title;
    }

    public Long getRecordLabelId() {
        return recordlabelId;
    }

    public void setRecordLabelId(Long recordLabelId) {
        this.recordlabelId = recordLabelId;
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
