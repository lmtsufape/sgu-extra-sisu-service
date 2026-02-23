package br.edu.ufape.sgu_extra_sisu_service.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;

public record PagamentoRequest(
        @NotNull BigDecimal amount,
        @NotBlank String customerName,
        @CPF String document, // Precisa ser modificado para aceitar cnpj
        MetodoPagamento type,
        DadosCartao cardDetails,
        DetalhesBoleto boletoDetails
) {}