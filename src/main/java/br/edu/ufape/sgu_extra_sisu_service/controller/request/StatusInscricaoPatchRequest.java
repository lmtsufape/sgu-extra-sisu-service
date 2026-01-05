package br.edu.ufape.sgu_extra_sisu_service.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatusInscricaoPatchRequest {
    
    private Long statusId;
    private String observacao;
}
