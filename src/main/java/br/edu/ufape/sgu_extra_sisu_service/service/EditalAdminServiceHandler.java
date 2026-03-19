package br.edu.ufape.sgu_extra_sisu_service.service;

import br.edu.ufape.sgu_extra_sisu_service.client.EditaisClient;
import br.edu.ufape.sgu_extra_sisu_service.client.EditalServiceClient;
import br.edu.ufape.sgu_extra_sisu_service.client.EtapaServiceClient;
import br.edu.ufape.sgu_extra_sisu_service.controller.request.AdminEditalRequest;
import br.edu.ufape.sgu_extra_sisu_service.controller.request.EtapaAdminRequest;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.AdminEditalDetalhadoResponse;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.HistoricoEtapaInscricaoResponse;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.InscricaoDetalhadaResponse;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.InscricaoResponse;
import br.edu.ufape.sgu_extra_sisu_service.service.interfaces.EditalAdminHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EditalAdminServiceHandler implements EditalAdminHandler {

    private final EditalServiceClient editalServiceClient;
    private final EtapaServiceClient etapaServiceClient;
    private final EditaisClient editaisClient;

    @Override
    public AdminEditalDetalhadoResponse buscarEditalComEtapas(Long id) {
        return new AdminEditalDetalhadoResponse(
                editalServiceClient.buscar(id),
                editalServiceClient.listarEtapasPorEdital(id)
        );
    }

    @Override
    public br.edu.ufape.sgu_extra_sisu_service.controller.response.EditalResponse editarEdital(Long id, AdminEditalRequest request) {
        return editalServiceClient.editar(id, request);
    }

    @Override
    public void deletarEdital(Long id) {
        editalServiceClient.deletar(id);
    }

    @Override
    public br.edu.ufape.sgu_extra_sisu_service.controller.response.EtapaResponse editarEtapa(Long id, EtapaAdminRequest request) {
        return etapaServiceClient.editar(id, request);
    }

    @Override
    public void deletarEtapa(Long id) {
        etapaServiceClient.deletar(id);
    }

    @Override
    public InscricaoDetalhadaResponse buscarDetalhesInscricao(Long id) {
        InscricaoResponse inscricao = editaisClient.buscarPorIdInscricao(id);
        List<HistoricoEtapaInscricaoResponse> historico = editaisClient.listarHistoricos().getContent().stream()
                .filter(item -> item.getInscricao() != null && id.equals(item.getInscricao().getId()))
                .sorted(Comparator.comparing(HistoricoEtapaInscricaoResponse::getDataAcao,
                        Comparator.nullsLast(Comparator.naturalOrder())))
                .toList();

        return new InscricaoDetalhadaResponse(inscricao, historico);
    }
}
