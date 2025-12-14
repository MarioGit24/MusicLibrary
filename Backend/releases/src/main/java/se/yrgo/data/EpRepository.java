package se.yrgo.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se.yrgo.domain.Ep;


@Repository
public interface EpRepository extends JpaRepository<Ep, Long>  {

    
} 
   