package br.edu.ufape.sgu_extra_sisu_service.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InscricaoRequest {
    
    private Long editalId;
    private Long statusAtualId; 
}