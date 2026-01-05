package br.edu.ufape.sgu_extra_sisu_service.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InscricaoResponse {

    private Long id;
    private UUID idUsuario;
    private LocalDateTime dataInscricao;
    private StatusPersonalizadoResponse statusAtual;
    //private documentos

}