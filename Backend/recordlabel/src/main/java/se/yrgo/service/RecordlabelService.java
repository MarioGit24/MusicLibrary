package se.yrgo.service;

import se.yrgo.domain.*;
import se.yrgo.dto.*;

public interface RecordlabelService {

    boolean enrollArtist(Long artistId, Long recordlabelId);

    RecordlabelResponseDTO getRecordlabelDetails(Long recordlabelId);

    Recordlabel createRecordlabel(Recordlabel recordlabel);

}
