package br.edu.ufape.sgu_extra_sisu_service.controller.response;

import br.edu.ufape.sgu_extra_sisu_service.model.enums.TipoStatus;
import lombok.Data;

@Data
public class StatusPersonalizadoResponse {
    private Long id;
    private String nome;
    private TipoStatus tipoStatus;
    private boolean concluiEtapa;
}