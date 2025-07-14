package edu.ifpr.trabalho_semestral.entities;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.time.LocalDate;

import java.util.Set;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Entity
@Data
public class VHS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private Integer code;
    
    @Column(nullable = false)
    private String image;
    
    @Column(nullable = false)
    private String director;
    
    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false)
    private LocalDate registrationDate;

    @Enumerated (EnumType.STRING)
    @Column(nullable = false)
    private TapeStatus status;

    @ManyToMany
    @JoinTable(
    name = "categorias", // nome da tabela no banco
    joinColumns = @JoinColumn(name = "id_vhs", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "id_categoria", referencedColumnName = "id")
    )
    private Set<Categoria> categorias;


    // Outros atributos e métodos conforme necessário
    public TapeStatus getStatus() {
        return status;

    }
}
