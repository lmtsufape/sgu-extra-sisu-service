package br.edu.ufape.sgu_extra_sisu_service.controller.request;

import lombok.Data;

@Data
public class AvaliarIsencaoRequest {
    public String status;
    public String justificativa;
}
