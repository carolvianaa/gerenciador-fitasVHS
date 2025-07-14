package edu.ifpr.trabalho_semestral.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ifpr.trabalho_semestral.entities.VHS;

public @Repository
interface VHSRepository extends JpaRepository<VHS, Long> {

    
}
