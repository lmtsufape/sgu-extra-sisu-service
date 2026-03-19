package br.edu.ufape.sgu_extra_sisu_service.controller.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdminEditalRequest {
    private String titulo;
    private String descricao;
    private LocalDateTime dataPublicacao;
    private LocalDateTime inicioInscricao;
    private LocalDateTime fimIncricao;
    private Long statusAtualId;
    private Long tipoEditalId;
    private Long idUnidadeAdministrativa;
    private Long cursoId;
}
