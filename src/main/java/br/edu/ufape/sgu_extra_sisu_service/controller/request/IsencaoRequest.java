package br.edu.ufape.sgu_extra_sisu_service.controller.request;

import lombok.Data;

import java.util.List;

@Data
public class IsencaoRequest {
    public Long usuarioId;
    public Long editalId;
    public Long modalidadeId;
    private List<String> documentoUrl;
}
