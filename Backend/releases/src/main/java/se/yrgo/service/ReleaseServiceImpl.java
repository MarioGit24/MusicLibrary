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
    private final SingleRepository singleRepository;

    public ReleaseServiceImpl(AlbumRepository albumRepository,
            EpRepository epRepository,
            SingleRepository singleRepository) {
        this.albumRepository = albumRepository;
        this.epRepository = epRepository;
        this.singleRepository = singleRepository;
    }

    @Override
    public List<ReleaseDTO> findAllByRecordlabelId(Long recordlabelId) {
        List<ReleaseDTO> releaseList = new ArrayList<>();

        List<Album> albums = albumRepository.findByRecordlabelId(recordlabelId);
        for (Album album : albums) {
            ReleaseDTO dto = new ReleaseDTO();
            dto.setId(album.getId());
            dto.setTitle(album.getTitle());
            dto.setType("ALBUM");

            List<SongDTO> songDtos = album.getSongs().stream()
                    .map(s -> new SongDTO(s.getId(), s.getTitle(), s.getDuration()))
                    .toList();
            dto.setSongs(songDtos);

            releaseList.add(dto);
        }

        List<Ep> eps = epRepository.findByRecordlabelId(recordlabelId);
        for (Ep ep : eps) {
            ReleaseDTO epDto = new ReleaseDTO();
            epDto.setId(ep.getId());
            epDto.setTitle(ep.getTitle());
            epDto.setType("EP");

            List<SongDTO> songDtos = ep.getSongs().stream()
                    .map(s -> new SongDTO(s.getId(), s.getTitle(), s.getDuration()))
                    .toList();
            epDto.setSongs(songDtos);

            releaseList.add(epDto);
        }

        List<Single> singles = singleRepository.findByRecordlabelId(recordlabelId);
        for (Single s : singles) {
            ReleaseDTO singleDto = new ReleaseDTO();
            singleDto.setId(s.getId());
            singleDto.setTitle(s.getTitle());
            singleDto.setType("SINGLE");

            List<SongDTO> singleSongList = List.of(
                    new SongDTO(s.getId(), s.getTitle(), s.getDuration()));
            singleDto.setSongs(singleSongList);

            releaseList.add(singleDto);
        }

        return releaseList;
    }
}