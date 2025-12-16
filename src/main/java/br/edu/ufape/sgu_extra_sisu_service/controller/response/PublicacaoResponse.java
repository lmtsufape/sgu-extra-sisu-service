package br.edu.ufape.sgu_extra_sisu_service.controller.response;

import java.time.LocalDateTime;

import br.edu.ufape.sgu_extra_sisu_service.model.Publicacao;
import lombok.Data;

@Data
public class PublicacaoResponse {
    private Long id;
    private String nome;
    private String status;
    private String pdf;
    private String texto;
    private LocalDateTime dataPublicacao;
    private Long editalId;

    public PublicacaoResponse(Publicacao obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.status = obj.getStatus();
        this.pdf = obj.getPdf();
        this.texto = obj.getTexto();
        this.dataPublicacao = obj.getDataPublicacao();
        if (obj.getEdital() != null) {
            this.editalId = obj.getEdital().getId();
        }
    }
}
