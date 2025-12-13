package se.yrgo.service;

import java.util.List;

import se.yrgo.domain.Single;

public interface SingleService {
    public List<Single> getSingles(); 

    public Single createSingle(Single single); 

}
