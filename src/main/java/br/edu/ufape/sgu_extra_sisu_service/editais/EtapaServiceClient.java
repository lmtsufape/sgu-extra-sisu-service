package br.edu.ufape.sgu_extra_sisu_service.editais;

import br.edu.ufape.sgu_extra_sisu_service.controller.request.EtapaRequest;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.EtapaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "sgu-editais-service", path = "/etapa")
public interface EtapaServiceClient {

   @PostMapping
    EtapaResponse salvar(@RequestBody EtapaRequest request);

    @GetMapping("/{id}")
    EtapaResponse buscar(@PathVariable("id") Long id);

    @GetMapping
    Page<EtapaResponse> listar(Pageable pageable);

    @DeleteMapping("/{id}")
    void deletar(@PathVariable("id") Long id);

    // Adicione os demais métodos do EtapaController conforme sua necessidade


}