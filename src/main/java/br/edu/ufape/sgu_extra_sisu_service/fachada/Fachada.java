package br.edu.ufape.sgu_extra_sisu_service.fachada;

import br.edu.ufape.sgu_extra_sisu_service.controller.request.DataEtapaRequest;
import br.edu.ufape.sgu_extra_sisu_service.controller.request.EtapaRequest;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.DataEtapaResponse;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.EtapaResponse;
import br.edu.ufape.sgu_extra_sisu_service.editais.DataEtapaServiceClient;
import br.edu.ufape.sgu_extra_sisu_service.service.EtapaServiceHandler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import com.querydsl.core.types.Predicate;

import br.edu.ufape.sgu_extra_sisu_service.model.EditalExtraSisu;
import br.edu.ufape.sgu_extra_sisu_service.service.interfaces.EditalExtraSisuService;

@Component
public class Fachada {


    @Autowired
    private EditalExtraSisuService extraSisuService;

    @Autowired
    private EtapaServiceHandler etapaHandler;

    @Autowired
    private DataEtapaServiceClient dataEtapaServiceClient;

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

    /* =========================== Etapas =================================*/

    public EtapaResponse criarEtapaNoEdital(EtapaRequest request) {
        return etapaHandler.salvarNoModuloEditais(request);
    }

    public EtapaResponse salvarEtapa(EtapaRequest request) {
        return etapaHandler.salvarNoModuloEditais(request);
    }

    public EtapaResponse buscarEtapa(Long id) {
        return etapaHandler.buscarNoModuloEditais(id);
    }

    public DataEtapaResponse salvarDataEtapa(@Valid DataEtapaRequest request) {
        return dataEtapaServiceClient.salvar(request);
    }
}