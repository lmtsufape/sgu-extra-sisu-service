package br.edu.ufape.sgu_extra_sisu_service.editais;

import br.edu.ufape.sgu_extra_sisu_service.controller.request.DataEtapaRequest;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.DataEtapaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "sgu-editais-service", path = "/data-etapa")
public interface DataEtapaServiceClient {

    @PostMapping
    DataEtapaResponse vincularDatas(@RequestBody DataEtapaRequest request);

    @PostMapping
    DataEtapaResponse salvar(@RequestBody DataEtapaRequest request);
}