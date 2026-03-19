package br.edu.ufape.sgu_extra_sisu_service.client;

import br.edu.ufape.sgu_extra_sisu_service.config.FeignClientConfig;
import br.edu.ufape.sgu_extra_sisu_service.controller.request.EtapaAdminRequest;
import br.edu.ufape.sgu_extra_sisu_service.controller.request.EtapaRequest;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.EtapaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "sgu-editais-service", path = "/etapa", configuration = FeignClientConfig.class)
public interface EtapaServiceClient {

   @PostMapping
    EtapaResponse salvar(@RequestBody EtapaRequest request);

    @GetMapping("/{id}")
    EtapaResponse buscar(@PathVariable("id") Long id);

    @PatchMapping("/{id}")
    EtapaResponse editar(@PathVariable("id") Long id, @RequestBody EtapaAdminRequest request);

    @GetMapping
    Page<EtapaResponse> listar(Pageable pageable);

    @DeleteMapping("/{id}")
    void deletar(@PathVariable("id") Long id);

    // Adicione os demais métodos do EtapaController conforme sua necessidade


}
