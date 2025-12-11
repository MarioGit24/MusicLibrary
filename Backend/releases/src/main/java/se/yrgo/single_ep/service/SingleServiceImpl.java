package se.yrgo.single_ep.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import se.yrgo.single_ep.data.SingleRepository;
import se.yrgo.single_ep.domain.Single;


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
