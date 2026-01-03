package br.edu.ufape.sgu_extra_sisu_service.controller.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class EtapaResponse {
    private Long id;
    private String nome;
    private String descricao;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private Long editalId;
}