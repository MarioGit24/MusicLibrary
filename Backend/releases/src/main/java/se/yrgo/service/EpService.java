package se.yrgo.service;

import java.util.*;

import se.yrgo.domain.*;

public interface EpService {
    public List<Ep> getEps();

    public Ep createEp(Ep ep);

    Ep getEpById(Long id);

    Ep updateEp(Long id, Ep ep);

    void deleteEp(Long id);

}
