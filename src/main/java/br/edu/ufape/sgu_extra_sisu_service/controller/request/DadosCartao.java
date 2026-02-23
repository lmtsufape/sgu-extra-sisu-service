package br.edu.ufape.sgu_extra_sisu_service.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.CreditCardNumber;

public record DadosCartao(
        @CreditCardNumber String number,
        @NotBlank String holderName,
        @Pattern(regexp = "^(0[1-9]|1[0-2])/[0-9]{2}$") String expirationDate,
        @Size(min = 3, max = 4) String cvv
) {
}
