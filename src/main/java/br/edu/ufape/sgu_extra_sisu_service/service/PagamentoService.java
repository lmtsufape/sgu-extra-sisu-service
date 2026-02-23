package br.edu.ufape.sgu_extra_sisu_service.service;

import br.edu.ufape.sgu_extra_sisu_service.controller.request.PagamentoRequest;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.PagamentoResponse;
import br.edu.ufape.sgu_extra_sisu_service.service.interfaces.PagamentoInterfaceHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PagamentoService {
    private final List<PagamentoInterfaceHandler> strategies;

    public PagamentoResponse executePayment(PagamentoRequest request) {
        return strategies.stream()
                .filter(s -> s.supports(request.type()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Método de pagamento não suportado"))
                .process(request);
    }
}
