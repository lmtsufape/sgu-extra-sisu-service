package br.edu.ufape.sgu_extra_sisu_service.fachada;

import br.edu.ufape.sgu_extra_sisu_service.controller.request.*;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.*;
import br.edu.ufape.sgu_extra_sisu_service.client.DataEtapaServiceClient;
import br.edu.ufape.sgu_extra_sisu_service.client.DocumentoServiceClient;
import br.edu.ufape.sgu_extra_sisu_service.client.TipoEditalServiceClient;
import br.edu.ufape.sgu_extra_sisu_service.service.EtapaServiceHandler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import com.querydsl.core.types.Predicate;

import br.edu.ufape.sgu_extra_sisu_service.controller.request.StatusPersonalizadoRequest;
import br.edu.ufape.sgu_extra_sisu_service.controller.request.ValorCampoRequest;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.InscricaoResponse;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.PageResponse;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.StatusPersonalizadoResponse;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.ValorCampoResponse;
import br.edu.ufape.sgu_extra_sisu_service.model.EditalExtraSisu;
import br.edu.ufape.sgu_extra_sisu_service.service.interfaces.EditalInscricaoHandler;
import br.edu.ufape.sgu_extra_sisu_service.service.interfaces.StatusPersonalizadoHandler;
import br.edu.ufape.sgu_extra_sisu_service.service.interfaces.ValorCampoHandler;
import lombok.RequiredArgsConstructor;
import br.edu.ufape.sgu_extra_sisu_service.service.interfaces.EditalExtraSisuService;

@Component
@RequiredArgsConstructor
public class Fachada {


    private final EditalExtraSisuService extraSisuService;
    private final EditalInscricaoHandler editalInscricaoHandler;
    private final StatusPersonalizadoHandler statusPersonalizadoHandler;
    private final ValorCampoHandler valorCampoHandler;

    @Autowired
    private EtapaServiceHandler etapaHandler;

    @Autowired
    private DataEtapaServiceClient dataEtapaServiceClient;

    @Autowired
    private DocumentoServiceClient documentoServiceClient;

    @Autowired
    private TipoEditalServiceClient tipoEditalServiceClient;

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

    // =================== Valor campo ===================

    public ValorCampoResponse salvarValorCampoExterno(ValorCampoRequest request) {
        return valorCampoHandler.salvar(request);
    }

    public ValorCampoResponse editarValorCampoExterno(Long id, ValorCampoRequest request) {
        return valorCampoHandler.atualizar(id, request);
    }

    public ValorCampoResponse buscarValorCampoExterno(Long id) {
        return valorCampoHandler.buscarPorId(id);
    }

    public PageResponse<ValorCampoResponse> listarValoresCampoExternos() {
        return valorCampoHandler.listar();
    }

    public void deletarValorCampoExterno(Long id) {
        valorCampoHandler.deletar(id);
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

    /* =========================== Documentos =================================*/

    public DocumentoResponse salvarDocumento(@Valid DocumentoRequest request) {
        return documentoServiceClient.salvar(request);
    }

    public DocumentoResponse editarDocumento(Long id, DocumentoRequest request) {
        return documentoServiceClient.editar(id, request);
    }

    public void deletarDocumento(Long id) {
         documentoServiceClient.deletar(id);
    }

    /*============================== TipoEdital ===============================*/

    public TipoEditalResponse salvarTipoEdital(TipoEditalRequest request) {
        return tipoEditalServiceClient.salvar(request);
    }

    public TipoEditalResponse buscarTipoEdital(Long id) {
        return tipoEditalServiceClient.buscar(id);
    }

    public Page<TipoEditalResponse> listarTiposEditais(Pageable pageable) {
        return tipoEditalServiceClient.listar(pageable);
    }

    public TipoEditalResponse editarTipoEdital(Long id, TipoEditalRequest request) {
        return tipoEditalServiceClient.editar(id, request);
    }

    public void deletarTipoEdital(Long id) {
        tipoEditalServiceClient.deletar(id);
    }

}