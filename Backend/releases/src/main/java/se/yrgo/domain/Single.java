package se.yrgo.domain;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class Single {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private int duration;
    private Long recordlabelId;
    private Long artistId;


    public Single() {}

    public Single(String title, int duration, Long recordlabelId, Long artistId){
        this.title = title;
        this.duration = duration;
        this.recordlabelId = recordlabelId;
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

    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public void setRecordlabelId(Long recordlabelId) {
        this.recordlabelId = recordlabelId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Single)) return false;
        Single single = (Single) o;
        return id != null && id.equals(single.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Single{id=" + id + ", title='" + title + "'}";
    }
}
