package se.yrgo.service;

import java.util.*;
import java.util.stream.*;

import org.springframework.stereotype.*;

import se.yrgo.data.*;
import se.yrgo.domain.*;
import se.yrgo.dto.*;

@Service
public class EpServiceImpl implements EpService {

    private final EpRepository epRepository;

    public EpServiceImpl(EpRepository epRepository) {
        this.epRepository = epRepository;
    }

    @Override
    public List<EpResponseDTO> getAllEps() {
        return epRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private EpResponseDTO convertToDTO(Ep ep) {
        EpResponseDTO response = new EpResponseDTO();
        response.setId(ep.getId());
        response.setTitle(ep.getTitle());
        response.setArtistId(ep.getArtistId());
        response.setRecordlabelId(ep.getRecordlabelId());

        if (ep.getSongs() != null) {
            List<SongDTO> songDTOs = ep.getSongs().stream()
                    .map(s -> new SongDTO(s.getId(), s.getTitle(), s.getDuration()))
                    .collect(Collectors.toList());
            response.setSongs(songDTOs);
        }
        return response;
    }

    @Override
    public EpResponseDTO createEp(EpCreationRequestDTO dto) {
        Ep ep = new Ep();
        ep.setTitle(dto.getTitle());
        ep.setArtistId(dto.getArtistId());
        ep.setRecordlabelId(dto.getRecordlabelId());

        if (dto.getSongsList() != null) {
            for (SongDTO sDto : dto.getSongsList()) {
                Song song = new Song(sDto.getTitle(), sDto.getDuration());
                song.setEp(ep);
                ep.getSongs().add(song);

            }

        }
        Ep savedEp = epRepository.save(ep);
        return convertToDTO(savedEp);
    }

    @Override
    public EpResponseDTO getEpById(Long id) {
        Ep ep = epRepository.findById(id).orElseThrow(() -> new RuntimeException("Ep was not found"));
        return convertToDTO(ep);
    }

    @Override
    public EpResponseDTO updateEp(Long id, EpCreationRequestDTO dto) {
        Ep existing = epRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ep was not found"));

        existing.setTitle(dto.getTitle());
        existing.setArtistId(dto.getArtistId());
        existing.setRecordlabelId(dto.getRecordlabelId());

        Ep savedEp = epRepository.save(existing);
        return convertToDTO(savedEp);
    }

    @Override
    public void deleteEp(Long id) {
        epRepository.deleteById(id);
    }

    @Override
    public List<EpResponseDTO> getEpsByRecordlabel(Long recordlabelId) {
        return epRepository.findByRecordlabelId(recordlabelId).stream().map(this::convertToDTO)
                .collect(Collectors.toList());
    }

}
