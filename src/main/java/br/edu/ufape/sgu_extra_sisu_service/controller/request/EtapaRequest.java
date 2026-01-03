package br.edu.ufape.sgu_extra_sisu_service.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class EtapaRequest {
    @NotBlank(message = "O nome da etapa é obrigatório")
    private String nome;

    private String descricao;

    @NotNull(message = "A data de início é obrigatória")
    private LocalDateTime dataInicio;

    @NotNull(message = "A data de fim é obrigatória")
    private LocalDateTime dataFim;

    private Long editalId;
}