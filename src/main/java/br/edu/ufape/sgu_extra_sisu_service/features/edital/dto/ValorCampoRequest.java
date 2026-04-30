package br.edu.ufape.sgu_extra_sisu_service.features.edital.dto;

import lombok.Data;

@Data
public class ValorCampoRequest {
    private String valor;
    private Long inscricaoId;
    private Long campoPersonalizadoId;
}