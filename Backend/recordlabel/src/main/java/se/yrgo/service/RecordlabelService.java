package se.yrgo.service;

import java.util.*;

import se.yrgo.single_ep.domain.*;

public interface RecordlabelService {
    List<RecordlabelResponseDTO> getAllRecordlabels();

    void createRecordlabel(Recordlabel recordlabel);

    boolean enrollArtist(Long artistId, Long recordlabelId);
}
