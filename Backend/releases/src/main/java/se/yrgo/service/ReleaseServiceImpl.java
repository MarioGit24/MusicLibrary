package se.yrgo.service;

import java.util.*;

import org.springframework.stereotype.*;

import se.yrgo.data.*;
import se.yrgo.domain.*;
import se.yrgo.dto.*;

@Service
public class ReleaseServiceImpl implements ReleaseService {

    private final AlbumRepository albumRepository;

    private final EpRepository epRepository; 
    // private final EpRepository epRepository; // You will add this later!

    public ReleaseServiceImpl(AlbumRepository albumRepository, EpRepository epRepository) {
        this.albumRepository = albumRepository;
        this.epRepository = epRepository; 

    }

    @Override
    public List<ReleaseDTO> findAllByRecordlabelId(Long recordlabelId) {
        // 1. Get all Albums for this label
        List<Album> albums = albumRepository.findByRecordlabelId(recordlabelId);

        // 2. Convert Albums to ReleaseDTOs
        List<ReleaseDTO> releaseList = new ArrayList<>();

        for (Album album : albums) {
            ReleaseDTO dto = new ReleaseDTO();
            dto.setId(album.getId());
            dto.setTitle(album.getTitle());
            dto.setType("ALBUM"); //

            List<SongDTO> songDtos = album.getSongs().stream()
                    .map(s -> new SongDTO(s.getTitle(), s.getDuration()))
                    .toList();
            dto.setSongs(songDtos);

            releaseList.add(dto);
        }

        // 3. (FUTURE) Get all EPs, convert them, and add to releaseList
        // List<Ep> eps = epRepository.findByLabelId(recordlabelId);
        // ... convert and add to releaseList ...

        List<Ep> eps = epRepository.findByRecordlabelId(recordlabelId); 

        for (Ep ep : eps) {
            ReleaseDTO epDto = new ReleaseDTO(); 
            epDto.setId(ep.getId());
            epDto.setTitle(ep.getTitle()); 
            epDto.setType("EP");
            
            List<SongDTO> songDtos2 = ep.getSongs().stream().map(s -> new SongDTO(s.getTitle(), s.getDuration())).toList(); 
            epDto.setSongs(songDtos2);

            releaseList.add(epDto); 
        }

        return releaseList;
    }
}