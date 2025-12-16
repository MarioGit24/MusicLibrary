package se.yrgo.data;

import java.util.*;

import org.springframework.data.jpa.repository.*;

import se.yrgo.domain.*;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    List<Album> findByRecordlabelId(Long recordlabelId);
}
