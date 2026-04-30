package br.edu.ufape.sgu_extra_sisu_service.infrastructure.integration.editais.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.edu.ufape.sgu_extra_sisu_service.features.documento.dto.DocumentoRequest;
import br.edu.ufape.sgu_extra_sisu_service.features.documento.dto.DocumentoResponse;

@FeignClient(name = "sgu-editais-service", contextId="documentoClient",path = "/documento")
public interface DocumentoClient {

    @PostMapping
    DocumentoResponse salvar(@RequestBody DocumentoRequest request);

    @GetMapping("/{id}")
    DocumentoResponse buscar(@PathVariable("id") Long id);

    @PatchMapping("/{id}")
    DocumentoResponse editar(@PathVariable("id") Long id, @RequestBody DocumentoRequest request);

    @DeleteMapping("/{id}")
    void deletar(@PathVariable("id") Long id);
}