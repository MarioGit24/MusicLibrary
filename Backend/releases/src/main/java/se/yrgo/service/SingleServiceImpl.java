package se.yrgo.service;

import java.util.*;
import java.util.stream.*;

import org.springframework.stereotype.*;

import se.yrgo.data.*;
import se.yrgo.domain.*;
import se.yrgo.dto.*;

@Service
public class SingleServiceImpl implements SingleService {

    private final SingleRepository singleRepository;

    public SingleServiceImpl(SingleRepository singleRepository) {
        this.singleRepository = singleRepository;
    }

    @Override
    public List<SingleResponseDTO> getSingles() {
        return singleRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SingleResponseDTO getSingleById(Long id) {
        Single single = singleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Single was not found"));
        return convertToDTO(single);
    }

    @Override
    public SingleResponseDTO createSingle(SingleRequestDTO dto) {
        Single single = new Single();
        single.setTitle(dto.getTitle());
        single.setDuration(dto.getDuration());
        single.setRecordlabelId(dto.getRecordlabelId());
        single.setArtistId(dto.getArtistId());

        Single saved = singleRepository.save(single);
        return convertToDTO(saved);
    }

    @Override
    public SingleResponseDTO updateSingle(Long id, SingleRequestDTO dto) {
        Single existing = singleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Single not found"));

        existing.setTitle(dto.getTitle());
        existing.setDuration(dto.getDuration());
        existing.setRecordlabelId(dto.getRecordlabelId());
        // Uppdatera även artist-kopplingen
        existing.setArtistId(dto.getArtistId());

        Single saved = singleRepository.save(existing);
        return convertToDTO(saved);
    }

    @Override
    public void deleteSingle(Long id) {
        singleRepository.deleteById(id);
    }

    private SingleResponseDTO convertToDTO(Single single) {
        SingleResponseDTO response = new SingleResponseDTO();
        response.setTitle(single.getTitle());
        response.setDuration(single.getDuration());
        response.setRecordlabelId(single.getRecordlabelId());
        response.setArtistId(single.getArtistId());
        return response;
    }
}