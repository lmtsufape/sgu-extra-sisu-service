package br.edu.ufape.sgu_extra_sisu_service.service.interfaces;

import br.edu.ufape.sgu_extra_sisu_service.controller.request.MetodoPagamento;
import br.edu.ufape.sgu_extra_sisu_service.controller.request.PagamentoRequest;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.PagamentoResponse;

public interface PagamentoInterfaceHandler {
    PagamentoResponse process(PagamentoRequest request);
    boolean supports(MetodoPagamento method);
}
