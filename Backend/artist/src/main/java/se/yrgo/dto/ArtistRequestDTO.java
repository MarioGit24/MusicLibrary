package se.yrgo.dto;

public class ArtistRequestDTO {

    private String name;
    private Long recordLabelId;

    public ArtistRequestDTO(){}

    public ArtistRequestDTO(String name, Long recordLabelId){
        this.name = name;
        this.recordLabelId = recordLabelId;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getRecordLabelId() {
        return recordLabelId;
    }

    public void setRecordLabelId(Long recordLabelId) {
        this.recordLabelId = recordLabelId;
    }

    


    
}
