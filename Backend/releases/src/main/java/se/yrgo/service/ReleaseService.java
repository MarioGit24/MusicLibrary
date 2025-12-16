package se.yrgo.service;

import java.util.*;

import se.yrgo.dto.*;

public interface ReleaseService {
    List<ReleaseDTO> findAllByRecordlabelId(Long recordlabelId);
}
