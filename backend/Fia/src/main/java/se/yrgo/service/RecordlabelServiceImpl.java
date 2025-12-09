package se.yrgo.service;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import se.yrgo.data.*;
import se.yrgo.domain.*;

@Service
public class RecordlabelServiceImpl implements RecordlabelService {
    @Autowired
    private RecordlabelRepository recordlabelRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Override
    public List<Recordlabel> getAllRecordlabels() {
        return recordlabelRepository.findAll();
    }

    @Override
    public void createRecordlabel(Recordlabel recordlabel) {
        recordlabelRepository.save(recordlabel);
    }

    @Override
    public boolean enrollArtist(Long artistId, Long recordlabelId) {
        Optional<Recordlabel> recordLabelOpt = recordlabelRepository.findById(recordlabelId);
        Optional<Artist> artistOpt = artistRepository.findById(artistId);

        if (recordLabelOpt.isEmpty() || artistOpt.isEmpty()) {
            return false;
        }

        Recordlabel recordlabel = recordLabelOpt.get();
        Artist artist = artistOpt.get();

        recordlabel.getArtists().add(artist);
        artist.setRecordlabel(recordlabel);

        recordlabelRepository.save(recordlabel);

        return true;
    }

}
