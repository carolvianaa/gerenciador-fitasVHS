package edu.ifpr.trabalho_semestral.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifpr.trabalho_semestral.entities.Categoria;
import edu.ifpr.trabalho_semestral.repositories.CategoriaRepository;

import java.util.List;
@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria findById(Long id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    public void save(Categoria categoria) {
        // Logic to save Categoria to the database
        categoriaRepository.save(categoria);
    }

    public void deleteById(Long id) {
        categoriaRepository.deleteById(id);
    }

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    //editar

    public Categoria update(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }


    public Categoria buscarPorNome(String nome) {
        return categoriaRepository.findAll().stream()
                .filter(c -> c.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);

        
    }
    // Outros métodos relacionados a Categoria, se necessário
    
}
