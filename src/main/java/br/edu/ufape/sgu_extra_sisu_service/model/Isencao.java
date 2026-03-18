package br.edu.ufape.sgu_extra_sisu_service.model;

import br.edu.ufape.sgu_extra_sisu_service.model.enums.StatusIsencao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Isencao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long usuarioId;

    @ManyToOne
    private EditalExtraSisu edital;

    @ManyToOne
    private Modalidade modalidade;

    @ElementCollection
    private List<String> documentosUrl;

    @Enumerated(EnumType.STRING)
    private StatusIsencao status; // PENDENTE, APROVADO, INDEFERIDO

    private String justificativa; // para comissao
}
