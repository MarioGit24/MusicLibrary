package se.yrgo.service;

import java.util.List;

import se.yrgo.dto.SingleRequestDTO;
import se.yrgo.dto.SingleResponseDTO;

public interface SingleService {
    List<SingleResponseDTO> getSingles(); 

    SingleResponseDTO createSingle(SingleRequestDTO single);
    
    SingleResponseDTO updateSingle(Long id, SingleRequestDTO single);

    SingleResponseDTO getSingleById(Long id);

    void deleteSingle(Long id);
}
