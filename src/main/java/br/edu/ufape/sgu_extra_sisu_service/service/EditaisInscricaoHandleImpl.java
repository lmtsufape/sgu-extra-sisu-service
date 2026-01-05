package br.edu.ufape.sgu_extra_sisu_service.service;

import br.edu.ufape.sgu_extra_sisu_service.client.EditaisClient;
import br.edu.ufape.sgu_extra_sisu_service.controller.request.InscricaoRequest;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.InscricaoResponse;
import br.edu.ufape.sgu_extra_sisu_service.controller.request.StatusInscricaoPatchRequest;
import br.edu.ufape.sgu_extra_sisu_service.service.interfaces.EditalInscricaoHandler;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EditaisInscricaoHandleImpl implements EditalInscricaoHandler {

    private final EditaisClient editaisServiceClient;
    
    private static final String SERVICE_NAME = "editaisServiceClient";

    @Override
    @CircuitBreaker(name = SERVICE_NAME, fallbackMethod = "fallbackRealizarInscricao")
    public InscricaoResponse realizarInscricao(Long editalId) {
        InscricaoRequest request = InscricaoRequest.builder()
                .editalId(editalId)
                .build();
        
        return editaisServiceClient.salvarInscricao(request);
    }

    @Override
    @CircuitBreaker(name = SERVICE_NAME, fallbackMethod = "fallbackBuscarInscricao")
    public InscricaoResponse buscarInscricaoPorId(Long id) {
        return editaisServiceClient.buscarPorIdInscricao(id);
    }

    @Override
    @CircuitBreaker(name = SERVICE_NAME, fallbackMethod = "fallbackListarMinhas")
    public List<InscricaoResponse> listarMinhasInscricoes() {
        return editaisServiceClient.listarMinhasInscricoes();
    }

    @Override
    @CircuitBreaker(name = SERVICE_NAME, fallbackMethod = "fallbackAtualizarStatus")
    public InscricaoResponse atualizarStatusInscricao(Long idInscricao, Long idNovoStatus, String observacao) {
        StatusInscricaoPatchRequest request = StatusInscricaoPatchRequest.builder()
                .statusId(idNovoStatus)
                .observacao(observacao)
                .build();
        
        return editaisServiceClient.atualizarStatusInscricao(idInscricao, request);
    }

    @Override
    @CircuitBreaker(name = SERVICE_NAME, fallbackMethod = "fallbackDeletar")
    public void deletarInscricao(Long id) {
        editaisServiceClient.deletarInscricao(id);
    }

    // ================= FALLBACKS =================

    public InscricaoResponse fallbackRealizarInscricao(Long editalId, Throwable t) {
        log.error("FALHA: Não foi possível realizar inscrição no edital {}. Erro: {}", editalId, t.getMessage());
        throw new RuntimeException("Serviço de Inscrições indisponível.");
    }

    public InscricaoResponse fallbackBuscarInscricao(Long id, Throwable t) {
        log.warn("FALHA: Erro ao buscar inscrição {}. Erro: {}", id, t.getMessage());
        return null;
    }

    public List<InscricaoResponse> fallbackListarMinhas(Throwable t) {
        log.warn("FALHA: Erro ao listar inscrições do usuário. Erro: {}", t.getMessage());
        return Collections.emptyList();
    }

    public InscricaoResponse fallbackAtualizarStatus(Long id, Long s, String o, Throwable t) {
        log.error("FALHA: Erro ao atualizar status da inscrição {}. Erro: {}", id, t.getMessage());
        throw new RuntimeException("Não foi possível atualizar o status.");
    }

    public void fallbackDeletar(Long id, Throwable t) {
        log.error("FALHA: Erro ao deletar inscrição {}. Erro: {}", id, t.getMessage());
        throw new RuntimeException("Serviço indisponível para deleção.");
    }
}
