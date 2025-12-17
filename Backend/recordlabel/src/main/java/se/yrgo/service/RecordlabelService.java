package se.yrgo.service;

import se.yrgo.dto.*;

public interface RecordlabelService {

    boolean enrollArtist(Long artistId, Long recordlabelId);

    RecordlabelResponseDTO getRecordlabelDetails(Long recordlabelId);

    RecordlabelResponseDTO createRecordlabel(RecordlabelRequestDTO recordDTO);

}
