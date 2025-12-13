package se.yrgo.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se.yrgo.domain.Single;


@Repository
public interface SingleRepository extends JpaRepository<Single, Long>  {


    
} 
    

