package se.yrgo.domain;

import jakarta.persistence.*;

@Entity
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;
    private String name;
    private Long recordlabelId;

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

    public Long getRecordlabelId() {
        return recordlabelId;
    }

    public void setRecordabelId(Long recordlabelId) {
        this.recordlabelId = recordlabelId;
    }

    // public Recordlabel getRecordlabel() {
    // return recordlabel;
    // }

    // public void setRecordlabel(Recordlabel recordlabel) {
    // this.recordlabel = recordlabel;
    // }

}
