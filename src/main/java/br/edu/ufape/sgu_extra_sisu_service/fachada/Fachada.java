package br.edu.ufape.sgu_extra_sisu_service.fachada;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import com.querydsl.core.types.Predicate;

import br.edu.ufape.sgu_extra_sisu_service.controller.request.StatusPersonalizadoRequest;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.InscricaoResponse;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.StatusPersonalizadoResponse;
import br.edu.ufape.sgu_extra_sisu_service.model.EditalExtraSisu;
import br.edu.ufape.sgu_extra_sisu_service.service.interfaces.EditalInscricaoHandler;
import br.edu.ufape.sgu_extra_sisu_service.service.interfaces.StatusPersonalizadoHandler;
import lombok.RequiredArgsConstructor;
import br.edu.ufape.sgu_extra_sisu_service.service.interfaces.EditalExtraSisuService;

@Component
@RequiredArgsConstructor
public class Fachada {


    private final EditalExtraSisuService extraSisuService;
    private final EditalInscricaoHandler editalInscricaoHandler;
    private final StatusPersonalizadoHandler statusPersonalizadoHandler;

    public EditalExtraSisu salvarEditalExtraSisu(EditalExtraSisu edital) {
        return extraSisuService.salvar(edital);
    }

    public EditalExtraSisu buscarEditalExtraSisu(Long id) {
        return extraSisuService.buscarPorId(id);
    }

    public Page<EditalExtraSisu> listarEditaisExtraSisu(Predicate filtro, Pageable pageable) {
        return extraSisuService.listarTodos(filtro, pageable);
    }

    public EditalExtraSisu atualizarEditalExtraSisu(Long id, EditalExtraSisu edital) {
        return extraSisuService.atualizar(id, edital);
    }

    public void deletarEditalExtraSisu(Long id) {
        extraSisuService.deletar(id);
    }


    // =================== Inscrição ===================

    public InscricaoResponse realizarInscricaoEmEdital(Long editalId) {
        return editalInscricaoHandler.realizarInscricao(editalId);
    }

    public List<InscricaoResponse> listarMinhasInscricoesExternas() {
        return editalInscricaoHandler.listarMinhasInscricoes();
    }

    public InscricaoResponse buscarInscricaoExternaPorId(Long idInscricao) {
        return editalInscricaoHandler.buscarInscricaoPorId(idInscricao);
    }

    public InscricaoResponse atualizarStatusInscricaoExterna(Long idInscricao, Long idNovoStatus, String observacao) {
        return editalInscricaoHandler.atualizarStatusInscricao(idInscricao, idNovoStatus, observacao);
    }

    public void deletarInscricaoExterna(Long idInscricao) {
        editalInscricaoHandler.deletarInscricao(idInscricao);
    }

    // =================== Status Personalizado ===================

    public List<StatusPersonalizadoResponse> listarStatusPersonalizados() {
        return statusPersonalizadoHandler.listarStatusPersonalizados();
    }

    public StatusPersonalizadoResponse buscarStatusPersonalizadoPorId(Long id) {
        return statusPersonalizadoHandler.buscarStatusPersonalizadoPorId(id);
    }
    
    public StatusPersonalizadoResponse salvarStatusPersonalizado(StatusPersonalizadoRequest request) {
        return statusPersonalizadoHandler.criarStatusPersonalizado(request);
    }
    
    public StatusPersonalizadoResponse editarStatusPersonalizado(Long id, StatusPersonalizadoRequest request) {
        return statusPersonalizadoHandler.atualizarStatusPersonalizado(id, request);
    }

}