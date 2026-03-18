package br.edu.ufape.sgu_extra_sisu_service.controller.response;

import br.edu.ufape.sgu_extra_sisu_service.model.Isencao;
import br.edu.ufape.sgu_extra_sisu_service.model.enums.StatusIsencao;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;

@Data
@NoArgsConstructor
public class IsencaoResponse {

    public Long id;
    public Long usuarioId;
    public Long editalId;
    public Long modalidadeId;
    public List<String> documentoUrl;
    public StatusIsencao status;
    public String justificativa;

    public IsencaoResponse(Isencao obj) {
        ModelMapper modelMapper = new ModelMapper();

        this.id = obj.getId();
        this.usuarioId = obj.getUsuarioId();
        this.editalId = obj.getEdital() != null ? obj.getEdital().getId() : null;
        this.modalidadeId = obj.getModalidade() != null ? obj.getModalidade().getId() : null;
        this.documentoUrl = obj.getDocumentosUrl();
        this.status = obj.getStatus();
        this.justificativa = obj.getJustificativa();
    }

}
