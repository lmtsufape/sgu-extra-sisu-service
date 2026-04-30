package br.edu.ufape.sgu_extra_sisu_service.features.inscricao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.UUID;

import br.edu.ufape.sgu_extra_sisu_service.controller.response.StatusPersonalizadoResponse;
import br.edu.ufape.sgu_extra_sisu_service.features.documento.dto.DocumentoResponse;
import br.edu.ufape.sgu_extra_sisu_service.features.edital.dto.EditalResponse;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InscricaoResponse {

    private Long id;
    private UUID idUsuario;
    private LocalDateTime dataInscricao;
    private EditalResponse edital;
    private StatusPersonalizadoResponse statusAtual;
    private List<DocumentoResponse> documentos;

}
