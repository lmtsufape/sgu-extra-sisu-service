package br.edu.ufape.sgu_extra_sisu_service.service.interfaces;

import br.edu.ufape.sgu_extra_sisu_service.controller.request.AdminEditalRequest;
import br.edu.ufape.sgu_extra_sisu_service.controller.request.EtapaAdminRequest;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.AdminEditalDetalhadoResponse;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.EditalResponse;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.EtapaResponse;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.InscricaoDetalhadaResponse;

public interface EditalAdminHandler {
    AdminEditalDetalhadoResponse buscarEditalComEtapas(Long id);
    EditalResponse editarEdital(Long id, AdminEditalRequest request);
    void deletarEdital(Long id);
    EtapaResponse editarEtapa(Long id, EtapaAdminRequest request);
    void deletarEtapa(Long id);
    InscricaoDetalhadaResponse buscarDetalhesInscricao(Long id);
}
