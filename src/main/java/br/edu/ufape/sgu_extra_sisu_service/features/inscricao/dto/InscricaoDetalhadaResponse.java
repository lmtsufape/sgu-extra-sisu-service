package br.edu.ufape.sgu_extra_sisu_service.features.inscricao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InscricaoDetalhadaResponse {
    private InscricaoResponse inscricao;
    private List<HistoricoEtapaInscricaoResponse> historico;
}
