package se.yrgo.single_ep.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se.yrgo.single_ep.domain.Single;


@Repository
public interface SingleRepository extends JpaRepository<Single, Long>  {


    
} 
    

