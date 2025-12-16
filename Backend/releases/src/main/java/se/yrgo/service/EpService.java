package se.yrgo.service;

import java.util.*;

import se.yrgo.domain.*;
import se.yrgo.dto.EpCreationRequestDTO;

public interface EpService {
    public List<Ep> getEps();

    public Ep createEp(EpCreationRequestDTO ep);

    Ep getEpById(Long id);

    Ep updateEp(Long id, Ep ep);

    void deleteEp(Long id);

    List<Ep> getEpsByRecordlabel(Long recordlabelId);

}
