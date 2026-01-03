package br.edu.ufape.sgu_extra_sisu_service.controller.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class DataEtapaResponse {
    private Long id;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
}
