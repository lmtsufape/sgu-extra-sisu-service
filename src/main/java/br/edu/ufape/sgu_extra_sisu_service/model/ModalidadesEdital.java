package br.edu.ufape.sgu_extra_sisu_service.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ModalidadesEdital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long editalId;

    @ManyToMany
    @JoinTable(
        name = "modalidades_edital_modalidade",
        joinColumns = @JoinColumn(name = "modalidades_edital_id"),
        inverseJoinColumns = @JoinColumn(name = "modalidade_id")
    )
    private List<Modalidade> modalidades;
}
