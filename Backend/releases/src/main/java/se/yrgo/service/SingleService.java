package se.yrgo.service;

import java.util.List;

import se.yrgo.domain.Single;

public interface SingleService {
    List<Single> getSingles(); 

    Single createSingle(Single single);
    
    Single updateSingle(Long id, Single single);

    Single getSingleById(Long id);

    void deleteSingle(Long id);
}
