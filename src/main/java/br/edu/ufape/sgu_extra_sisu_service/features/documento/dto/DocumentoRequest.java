package br.edu.ufape.sgu_extra_sisu_service.features.documento.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DocumentoRequest {
    @NotBlank(message = "nome é obrigatório")
    private String nome;

    @NotBlank(message = "caminho é obrigatório")
    private String caminho;
    private LocalDateTime dataUpload;
    private Long etapaId;
    private Long inscricaoId;
}