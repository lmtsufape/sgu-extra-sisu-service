package br.edu.ufape.sgu_extra_sisu_service.features.edital.dto;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;

import br.edu.ufape.sgu_extra_sisu_service.controller.response.StatusPersonalizadoResponse;
import br.edu.ufape.sgu_extra_sisu_service.features.tipoedital.dto.TipoEditalResponse;
import br.edu.ufape.sgu_extra_sisu_service.model.EditalExtraSisu;
import lombok.Data;

@Data
public class EditalResponse {
    private Long id;
    private String titulo;
    private String descricao;
    private String pdf;
    private LocalDateTime dataPublicacao;
    private LocalDateTime inicioInscricao;
    private LocalDateTime fimIncricao;
    private LocalDateTime dataInscricao;
    private LocalDateTime dataFinalizacao;
    private StatusPersonalizadoResponse statusAtual;
    private TipoEditalResponse tipoEdital;

    public EditalResponse(EditalExtraSisu obj) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);

        modelMapper.map(obj, this);
    }
}
