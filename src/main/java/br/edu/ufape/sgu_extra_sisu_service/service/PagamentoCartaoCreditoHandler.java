package br.edu.ufape.sgu_extra_sisu_service.service;

import br.edu.ufape.sgu_extra_sisu_service.controller.request.DadosCartao;
import br.edu.ufape.sgu_extra_sisu_service.controller.request.MetodoPagamento;
import br.edu.ufape.sgu_extra_sisu_service.controller.request.PagamentoRequest;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.PagamentoResponse;
import br.edu.ufape.sgu_extra_sisu_service.service.interfaces.PagamentoInterfaceHandler;
import org.springframework.stereotype.Service;

@Service
public class PagamentoCartaoCreditoHandler implements PagamentoInterfaceHandler {

    @Override
    public PagamentoResponse process(PagamentoRequest request) {
        DadosCartao cartao = request.cardDetails();

        if (cartao == null) {
            return new PagamentoResponse("ERRO", "Dados do cartão não informados.");
        }

        if (isExpired(cartao.expirationDate())) {
            return new PagamentoResponse("ERRO", "Cartão com data de validade vencida.");
        }

        System.out.println("Processando pagamento para: " + request.customerName());
        return new PagamentoResponse("SUCESSO", "Pagamento de R$ " + request.amount() + " autorizado.");
    }

    private boolean isExpired(String expirationDate) {
        return false;
    }

    @Override
    public boolean supports(MetodoPagamento method) {
        return method == MetodoPagamento.CREDITO;
    }
}
