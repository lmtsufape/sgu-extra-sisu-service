package br.edu.ufape.sgu_extra_sisu_service.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminEditalDetalhadoResponse {
    private EditalResponse edital;
    private List<EtapaResponse> etapas;
}
