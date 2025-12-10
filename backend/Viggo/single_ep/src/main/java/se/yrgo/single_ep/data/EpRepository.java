package se.yrgo.data;

import org.springframework.data.jpa.repository.JpaRepository;

import se.yrgo.domain.Ep;

public interface EpRepository extends JpaRepository<Ep, Long>  {

    
} 
   