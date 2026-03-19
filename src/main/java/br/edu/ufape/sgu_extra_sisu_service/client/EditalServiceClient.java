package br.edu.ufape.sgu_extra_sisu_service.client;

import br.edu.ufape.sgu_extra_sisu_service.config.FeignClientConfig;
import br.edu.ufape.sgu_extra_sisu_service.controller.request.AdminEditalRequest;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.EditalResponse;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.EtapaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "sgu-editais-service", path = "/edital", configuration = FeignClientConfig.class)
public interface EditalServiceClient {

    @GetMapping("/{id}")
    EditalResponse buscar(@PathVariable("id") Long id);

    @GetMapping("/{id}/etapas")
    List<EtapaResponse> listarEtapasPorEdital(@PathVariable("id") Long id);

    @PatchMapping("/{id}")
    EditalResponse editar(@PathVariable("id") Long id, @RequestBody AdminEditalRequest request);

    @DeleteMapping("/{id}")
    void deletar(@PathVariable("id") Long id);
}
