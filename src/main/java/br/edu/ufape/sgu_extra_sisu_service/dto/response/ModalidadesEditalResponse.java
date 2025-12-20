package br.edu.ufape.sgu_extra_sisu_service.dto.response;

import java.util.List;

import br.edu.ufape.sgu_extra_sisu_service.model.ModalidadesEdital;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModalidadesEditalResponse {

    private Long id;
    private Long editalId;
    private List<ModalidadeResponse> modalidades;

    public static ModalidadesEditalResponse fromEntity(ModalidadesEdital entity) {
        return new ModalidadesEditalResponse(
            entity.getId(),
            entity.getEditalId(),
            entity.getModalidades()
                  .stream()
                  .map(ModalidadeResponse::fromEntity)
                  .toList()
        );
    }
}
