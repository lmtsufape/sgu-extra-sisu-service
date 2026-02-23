package br.edu.ufape.sgu_extra_sisu_service.service;

import br.edu.ufape.sgu_extra_sisu_service.controller.request.MetodoPagamento;
import br.edu.ufape.sgu_extra_sisu_service.controller.request.PagamentoRequest;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.PagamentoResponse;
import br.edu.ufape.sgu_extra_sisu_service.service.interfaces.PagamentoInterfaceHandler;
import org.springframework.stereotype.Service;

@Service
public class PagamentoBoletoHandler implements PagamentoInterfaceHandler {

        @Override
        public PagamentoResponse process(PagamentoRequest request) {

            //tapa buraco no momento
            String barcode = "34191.79001 01043.510047 91020.150008 5 90000000026000";
            String pdfUrl = "https://provedor.com.br/boleto/xyz";

            return new PagamentoResponse("PENDING", "Boleto gerado com sucesso", barcode, pdfUrl);
        }

        @Override
        public boolean supports(MetodoPagamento method) {
            return method == MetodoPagamento.BOLETO;
        }

}
