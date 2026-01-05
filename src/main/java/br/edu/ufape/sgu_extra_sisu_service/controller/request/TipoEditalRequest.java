package br.edu.ufape.sgu_extra_sisu_service.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TipoEditalRequest {
    @NotBlank(message = "O nome do modelo é obrigatório")
    private String nome;
    private String descricao;
}