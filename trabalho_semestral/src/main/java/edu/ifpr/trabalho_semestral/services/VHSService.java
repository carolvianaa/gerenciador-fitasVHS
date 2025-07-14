package edu.ifpr.trabalho_semestral.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifpr.trabalho_semestral.entities.VHS;
import edu.ifpr.trabalho_semestral.repositories.VHSRepository;

@Service
public class VHSService {

    @Autowired
    private VHSRepository vhsRepository; // Assuming you have a VHSRepository interface for database operations
    
    public void save(VHS vhs) {
        
        // Logic to save VHS tape to the database
        vhsRepository.save(vhs);
        
    }  
    
    public List<VHS> findAll() {
        
        return vhsRepository.findAll();

    }
    //deletar by id
    public void deleteById(Long id) {
        vhsRepository.deleteById(id);
    }

    public VHS findById(Long id) {
        return vhsRepository.findById(id).orElse(null);
    }
}

