package br.edu.ufape.sgu_extra_sisu_service.service;

import br.edu.ufape.sgu_extra_sisu_service.controller.request.EtapaRequest;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.EtapaResponse;
import br.edu.ufape.sgu_extra_sisu_service.editais.EtapaServiceClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EtapaServiceHandler {
    private final EtapaServiceClient etapaClient;

    @CircuitBreaker(name = "etapaClient", fallbackMethod = "fallbackBuscar")
    public EtapaResponse buscarNoModuloEditais(Long id) {
        return etapaClient.buscar(id);
    }

    public EtapaResponse salvarNoModuloEditais(EtapaRequest request) {

        return etapaClient.salvar(request);
    }


}