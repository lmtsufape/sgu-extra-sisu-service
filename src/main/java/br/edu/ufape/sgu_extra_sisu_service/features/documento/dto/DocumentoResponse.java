package br.edu.ufape.sgu_extra_sisu_service.features.documento.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class DocumentoResponse {
    private Long id;
    private String nome;
    private String caminho;
    private LocalDateTime dataUpload;
    private Long etapaId;
}