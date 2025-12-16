package se.yrgo.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se.yrgo.domain.Ep;

@Repository
public interface EpRepository extends JpaRepository<Ep, Long> {

    public List<Ep> findByRecordlabelId(Long recordlabelId); 

}
