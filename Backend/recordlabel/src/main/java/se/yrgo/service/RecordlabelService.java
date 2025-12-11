package se.yrgo.service;

import java.util.*;

import se.yrgo.domain.*;

public interface RecordlabelService {
    List<Recordlabel> getAllRecordlabels();

    void createRecordlabel(Recordlabel recordlabel);

    boolean enrollArtist(Long artistId, Long recordlabelId);
}
