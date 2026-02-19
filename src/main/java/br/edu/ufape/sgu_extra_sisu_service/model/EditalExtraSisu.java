package br.edu.ufape.sgu_extra_sisu_service.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class EditalExtraSisu extends GrupoModeloEdital {
    
    private String titulo;
    private String descricao;
    @Column(columnDefinition = "TEXT")
    private String pdf;
    private LocalDateTime dataInscricao;
    private LocalDateTime dataFinalizacao;
    //private Long tipoEditalId;
}