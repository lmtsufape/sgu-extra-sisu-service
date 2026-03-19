package br.edu.ufape.sgu_extra_sisu_service.controller.response;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class EtapaResponse {
    private Long id;
    private String nome;
    private String descricao;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private boolean obrigatoria;
    private Integer ordem;
    private Long editalId;
    private StatusPersonalizadoResponse statusAtual;
    private List<Object> camposPersonalizados;
}
