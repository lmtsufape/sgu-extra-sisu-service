package br.edu.ufape.sgu_extra_sisu_service.features.inscricao.dto;

import lombok.Data;

import java.util.List;

@Data
public class IsencaoRequest {
    public Long usuarioId;
    public Long editalId;
    public Long modalidadeId;
    private List<String> documentoUrl;
}
