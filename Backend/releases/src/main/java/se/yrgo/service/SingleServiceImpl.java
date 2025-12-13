package se.yrgo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import se.yrgo.data.SingleRepository;
import se.yrgo.domain.Single;


@Service
public class SingleServiceImpl implements SingleService {

    private final SingleRepository singleRepository; 

    public SingleServiceImpl(SingleRepository singleRepository) {
        this.singleRepository = singleRepository;
    }

    @Override
    public List<Single> getSingles() {
    List<Single> singles = new ArrayList<>(); 
    singles = singleRepository.findAll(); 
    return singles; 
    }

    @Override
    public Single createSingle(Single single) {
       return singleRepository.save(single); 
    }
    
}
