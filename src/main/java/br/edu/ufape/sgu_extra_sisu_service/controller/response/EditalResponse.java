package br.edu.ufape.sgu_extra_sisu_service.controller.response;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper; 

import br.edu.ufape.sgu_extra_sisu_service.model.EditalExtraSisu;
import lombok.Data;

@Data
public class EditalResponse {
    private Long id;
    private String nome;
    private String pdf;
    private LocalDateTime dataInscricao;
    private LocalDateTime dataFinalizacao;

    public EditalResponse(EditalExtraSisu obj) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);

        modelMapper.map(obj, this);
    }
}