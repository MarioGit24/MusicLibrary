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

}
