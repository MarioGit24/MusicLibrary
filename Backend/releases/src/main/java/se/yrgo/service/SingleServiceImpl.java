package se.yrgo.service;

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
        return singleRepository.findAll();
    }

    @Override
    public Single getSingleById(Long id) {
        return singleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Single not found"));
    }

    @Override
    public Single createSingle(Single single) {
        return singleRepository.save(single);
    }

    @Override
    public Single updateSingle(Long id, Single single) {
        Single existing = getSingleById(id);
        existing.setTitle(single.getTitle());
        existing.setAlbum(single.getAlbum()); 
        return singleRepository.save(existing);
    }

    @Override
    public void deleteSingle(Long id) {
        singleRepository.deleteById(id);
    }
}
