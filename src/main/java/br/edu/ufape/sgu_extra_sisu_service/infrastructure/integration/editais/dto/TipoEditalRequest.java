package br.edu.ufape.sgu_extra_sisu_service.infrastructure.integration.editais.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TipoEditalRequest(
    @NotBlank(message = "O nome do tipo de edital é obrigatório")
    @Size(max = 50, message = "O nome do tipo de edital deve ter no máximo 50 caracteres")
    String nome,

    @NotBlank(message = "A descrição do tipo de edital é obrigatória")
    @Size(max = 150, message = "A descrição do tipo de edital deve ter no máximo 150 caracteres")
    String descricao
) {}
