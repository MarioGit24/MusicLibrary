package se.yrgo.single_ep.service;

import java.util.List;

import se.yrgo.single_ep.domain.Ep;



public interface EpService {
    public List<Ep> getEps();
    
    public Ep createEp(Ep ep); 

}
