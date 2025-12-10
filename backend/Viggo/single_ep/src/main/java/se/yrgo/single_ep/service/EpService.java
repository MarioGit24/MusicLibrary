package se.yrgo.service;

import java.util.List;

import se.yrgo.domain.Ep;

public interface EpService {
    public List<Ep> getEps();
    
    public Ep createEp(Ep ep); 

}
