package br.edu.ufape.sgu_extra_sisu_service.controller.request;

import lombok.Data;
import br.edu.ufape.sgu_extra_sisu_service.model.enums.TipoStatus;

@Data
public class StatusPersonalizadoRequest {
    private String nome;
    private TipoStatus tipoStatus;
    private boolean concluiEtapa;
}
