package se.yrgo.single_ep.service;

import java.util.List;

import se.yrgo.single_ep.domain.Single;

public interface SingleService {
    public List<Single> getSingles(); 

    public Single createSingle(Single single); 

}
