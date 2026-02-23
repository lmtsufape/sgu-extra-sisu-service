package br.edu.ufape.sgu_extra_sisu_service.controller;

import br.edu.ufape.sgu_extra_sisu_service.controller.request.PagamentoRequest;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.PagamentoResponse;
import br.edu.ufape.sgu_extra_sisu_service.service.PagamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pagamentos")
@RequiredArgsConstructor
public class PagamentoController {

        private final PagamentoService paymentService;

        @PostMapping
        public ResponseEntity<PagamentoResponse> processarPagamento(@Valid @RequestBody PagamentoRequest request) {
            PagamentoResponse response = paymentService.executePayment(request);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }

}
