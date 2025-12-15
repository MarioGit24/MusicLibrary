package se.yrgo.domain;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class Single {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    public Single() {}

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
