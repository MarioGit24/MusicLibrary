package se.yrgo.dto;

public class ArtistResponseDTO {
    private Long id;

    private String name;
    private Long recordLabelId;

    public ArtistResponseDTO(){}

    public ArtistResponseDTO(Long id, String name, Long recordLabelId){
        this.id = id;
        this.name = name;
        this.recordLabelId = recordLabelId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
