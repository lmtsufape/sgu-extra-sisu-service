package br.edu.ufape.sgu_extra_sisu_service.infrastructure.integration.editais.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.edu.ufape.sgu_extra_sisu_service.features.edital.dto.DataEtapaRequest;
import br.edu.ufape.sgu_extra_sisu_service.features.edital.dto.DataEtapaResponse;

@FeignClient(name = "sgu-editais-service", contextId = "dataEtapaClient", path = "/data-etapa")
public interface DataEtapaClient {

    @PostMapping
    DataEtapaResponse vincularDatas(@RequestBody DataEtapaRequest request);

    @PostMapping
    DataEtapaResponse salvar(@RequestBody DataEtapaRequest request);
}