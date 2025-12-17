package se.yrgo.service;

import java.util.*;

import se.yrgo.domain.*;
import se.yrgo.dto.EpCreationRequestDTO;
import se.yrgo.dto.EpResponseDTO;

public interface EpService {
    public List<EpResponseDTO> getAllEps();

    public EpResponseDTO createEp(EpCreationRequestDTO ep);

    EpResponseDTO getEpById(Long id);

    EpResponseDTO updateEp(Long id, EpCreationRequestDTO ep);

    void deleteEp(Long id);

    List<EpResponseDTO> getEpsByRecordlabel(Long recordlabelId);

}
