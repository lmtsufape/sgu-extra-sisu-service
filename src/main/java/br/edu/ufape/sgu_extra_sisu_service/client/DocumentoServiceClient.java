package br.edu.ufape.sgu_extra_sisu_service.client;

import br.edu.ufape.sgu_extra_sisu_service.controller.request.DocumentoRequest;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.DocumentoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "sgu-editais-service", path = "/documento")
public interface DocumentoServiceClient {

    @PostMapping
    DocumentoResponse salvar(@RequestBody DocumentoRequest request);

    @GetMapping("/{id}")
    DocumentoResponse buscar(@PathVariable("id") Long id);

    @PatchMapping("/{id}")
    DocumentoResponse editar(@PathVariable("id") Long id, @RequestBody DocumentoRequest request);

    @DeleteMapping("/{id}")
    void deletar(@PathVariable("id") Long id);
}