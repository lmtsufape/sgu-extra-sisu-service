package br.edu.ufape.sgu_extra_sisu_service.features.edital.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class DataEtapaRequest {
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private Long etapaId;
    private Long editalId;
}