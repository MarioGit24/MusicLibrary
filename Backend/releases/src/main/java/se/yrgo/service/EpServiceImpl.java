package se.yrgo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import se.yrgo.data.EpRepository;
import se.yrgo.domain.Ep;
import se.yrgo.domain.Song;
import se.yrgo.dto.EpCreationRequestDTO;

@Service
public class EpServiceImpl implements EpService {

    private final EpRepository epRepository;

    public EpServiceImpl(EpRepository epRepository) {
        this.epRepository = epRepository;
    }

    @Override
    public List<Ep> getAllEps() {
        return epRepository.findAll();
    }

    @Override
    public Ep createEp(EpCreationRequestDTO dto) {
        Ep ep = new Ep();
        ep.setTitle(dto.getTitle());
        ep.setArtistId(dto.getArtistId());
        ep.setRecordlabelId(dto.getRecordlabelId());
   
        if (dto.getSongs() != null) {
            dto.getSongs().forEach(songDto -> {
                Song song = new Song();
                song.setTitle(songDto.getTitle());
                song.setDuration(songDto.getDuration());

                ep.addSong(song);
            });

        }
        return epRepository.save(ep);
    }

    @Override
    public Ep getEpById(Long id) {
        return epRepository.findById(id).orElseThrow(() -> new RuntimeException("ep was not found"));
    }

    @Override
    public Ep updateEp(Long id, Ep ep) {
        Ep existing = getEpById(id);
        existing.setTitle(ep.getTitle());
        existing.setSongs(ep.getSongs());
        return epRepository.save(existing);
    }

    @Override
    public void deleteEp(Long id) {
        epRepository.deleteById(id);
    }

    @Override
    public List<Ep> getEpsByRecordlabel(Long recordlabelId) {
        return epRepository.findByRecordlabelId(recordlabelId);
    }

}
