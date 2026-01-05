package br.edu.ufape.sgu_extra_sisu_service.service.interfaces;

import br.edu.ufape.sgu_extra_sisu_service.controller.response.InscricaoResponse;
import java.util.List;

public interface EditalInscricaoHandler {
    InscricaoResponse realizarInscricao(Long editalId);
    InscricaoResponse buscarInscricaoPorId(Long id);
    List<InscricaoResponse> listarMinhasInscricoes();
    InscricaoResponse atualizarStatusInscricao(Long idInscricao, Long idNovoStatus, String observacao);
    void deletarInscricao(Long id);
}
