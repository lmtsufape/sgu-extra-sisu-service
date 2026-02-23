package br.edu.ufape.sgu_extra_sisu_service.controller.request;

import jakarta.validation.constraints.NotBlank;

public record DetalhesBoleto(
        @NotBlank String address,
        @NotBlank String city,
        @NotBlank String state
) {
}
