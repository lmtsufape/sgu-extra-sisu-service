package br.edu.ufape.sgu_extra_sisu_service.dto.request;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModalidadesEditalRequest {

    @NotNull
    private Long editalId;

    @NotEmpty
    private List<Long> modalidadesIds;
}