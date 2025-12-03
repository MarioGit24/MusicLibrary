package se.yrgo.service;

import java.util.*;

import org.springframework.beans.factory.annotation.*;

import se.yrgo.data.*;
import se.yrgo.domain.*;

public class RecordlabelServiceImpl implements RecordlabelService {
    @Autowired
    private RecordlabelRepository recordlabelRepository;

    @Override
    public List<Recordlabel> getAllRecordlabels() {
        return recordlabelRepository.findAll();
    }

    @Override
    public void createRecordlabel(Recordlabel recordlabel) {
        recordlabelRepository.save(recordlabel);
    }

}
