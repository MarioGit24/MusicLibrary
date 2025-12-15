package se.yrgo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import se.yrgo.data.EpRepository;

import se.yrgo.domain.Ep;

@Service
public class EpServiceImpl implements EpService {

    private final EpRepository epRepository;

    public EpServiceImpl(EpRepository epRepository) {
        this.epRepository = epRepository;
    }

    @Override
    public List<Ep> getEps() {
        return epRepository.findAll();
    }

    @Override
    public Ep createEp(Ep ep) {
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

}
