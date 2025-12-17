package se.yrgo.dto;

public class RecordlabelRequestDTO {

    private String labelName;

    public RecordlabelRequestDTO(){}

    public RecordlabelRequestDTO(String labelName){
        this.labelName = labelName;
    }


    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    

    
}
