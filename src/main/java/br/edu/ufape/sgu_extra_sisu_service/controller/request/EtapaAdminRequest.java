package br.edu.ufape.sgu_extra_sisu_service.controller.request;

import lombok.Data;

@Data
public class EtapaAdminRequest {
    private String nome;
    private String descricao;
    private boolean obrigatoria;
    private int ordem;
    private Long editalId;
    private Long tipoEditalModeloId;
    private Long statusAtualId;
}
