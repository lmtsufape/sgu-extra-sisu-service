package br.edu.ufape.sgu_extra_sisu_service.controller.response;

import lombok.Data;
import java.util.List;

@Data
public class TipoEditalResponse {
    private Long id;
    private String nome;
    private String descricao;
    // Simplificado: etapas e campos podem ser adicionados se necessário
}
