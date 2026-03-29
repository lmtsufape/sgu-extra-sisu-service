package br.edu.ufape.sgu_extra_sisu_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufape.sgu_extra_sisu_service.client.DocumentoServiceClient;
import br.edu.ufape.sgu_extra_sisu_service.controller.request.DocumentoRequest;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.DocumentoResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/documentos")
@RequiredArgsConstructor
public class DocumentoController {

    private DocumentoServiceClient documentoServiceClient;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DocumentoResponse salvar(@RequestBody @Valid DocumentoRequest request) {
        return documentoServiceClient.salvar(request);
    }

    @PatchMapping("/{id}")
    public DocumentoResponse editar(@PathVariable Long id, @RequestBody DocumentoRequest request) {
        return documentoServiceClient.editar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        documentoServiceClient.deletar(id);
    }
}