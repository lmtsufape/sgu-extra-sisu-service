package br.edu.ufape.sgu_extra_sisu_service.dto.response;

import br.edu.ufape.sgu_extra_sisu_service.model.Modalidade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModalidadeResponse {

    private Long id;
    private String nome;
    private String descricao;

    public static ModalidadeResponse fromEntity(Modalidade modalidade) {
        return new ModalidadeResponse(
            modalidade.getId(),
            modalidade.getNome(),
            modalidade.getDescricao()
        );
    }
}
