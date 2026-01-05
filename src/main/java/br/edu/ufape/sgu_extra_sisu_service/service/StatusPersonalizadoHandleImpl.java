package br.edu.ufape.sgu_extra_sisu_service.service;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import br.edu.ufape.sgu_extra_sisu_service.client.EditaisClient;
import br.edu.ufape.sgu_extra_sisu_service.controller.request.StatusPersonalizadoRequest;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.StatusPersonalizadoResponse;
import br.edu.ufape.sgu_extra_sisu_service.service.interfaces.StatusPersonalizadoHandler;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatusPersonalizadoHandleImpl implements StatusPersonalizadoHandler {
    
    private final EditaisClient editaisServiceClient;


    @Override
    @CircuitBreaker(name = "editaisServiceClient", fallbackMethod = "fallbackListarStatus")
    public List<StatusPersonalizadoResponse> listarStatusPersonalizados() {
        return editaisServiceClient.listarStatusPersonalizados();
    }

    public List<StatusPersonalizadoResponse> fallbackListarStatus(Throwable t) {
        log.error("Erro ao listar status: {}", t.getMessage());
        return Collections.emptyList(); 
    }


    @Override
    @CircuitBreaker(name = "editaisServiceClient", fallbackMethod = "fallbackBuscarStatus")
    public StatusPersonalizadoResponse buscarStatusPersonalizadoPorId(Long id) {
        return editaisServiceClient.buscarStatusPersonalizadoPorId(id);
    }

    public StatusPersonalizadoResponse fallbackBuscarStatus(Long id, Throwable t) {
        log.error("Erro ao buscar status ID {}: {}", id, t.getMessage());
        throw new RuntimeException("Serviço indisponível temporariamente.");
    }


    @Override
    @CircuitBreaker(name = "editaisServiceClient", fallbackMethod = "fallbackCriarStatus")
    public StatusPersonalizadoResponse criarStatusPersonalizado(StatusPersonalizadoRequest request) {
        return editaisServiceClient.criarStatusPersonalizado(request);
    }

    public StatusPersonalizadoResponse fallbackCriarStatus(StatusPersonalizadoRequest request, Throwable t) {
        log.error("Erro ao criar status: {}", t.getMessage());
        throw new RuntimeException("Não foi possível criar o status no momento.");
    }


    @Override
    @CircuitBreaker(name = "editaisServiceClient", fallbackMethod = "fallbackAtualizarStatus") 
    public StatusPersonalizadoResponse atualizarStatusPersonalizado(Long id, StatusPersonalizadoRequest request) {
        return editaisServiceClient.atualizarStatusPersonalizado(id, request);
    }

    public StatusPersonalizadoResponse fallbackAtualizarStatus(Long id, StatusPersonalizadoRequest request, Throwable t) {
        log.error("Erro ao atualizar status ID {}: {}", id, t.getMessage());
        throw new RuntimeException("Não foi possível atualizar o status no momento.");
    }

}