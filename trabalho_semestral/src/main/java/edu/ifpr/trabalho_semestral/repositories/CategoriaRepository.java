package edu.ifpr.trabalho_semestral.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edu.ifpr.trabalho_semestral.entities.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    // Métodos específicos para Categoria, se necessário
    //findbyname
   


}
