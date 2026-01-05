package br.edu.ufape.sgu_extra_sisu_service.service;

import br.edu.ufape.sgu_extra_sisu_service.controller.request.DocumentoRequest;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.DocumentoResponse;
import br.edu.ufape.sgu_extra_sisu_service.client.DocumentoServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DocumentoServiceHandler {
    private final DocumentoServiceClient documentoClient;

    public DocumentoResponse salvarNoModuloEditais(DocumentoRequest request) {
        return documentoClient.salvar(request);
    }

    public DocumentoResponse editarNoModuloEditais(Long id, DocumentoRequest request) {
        return documentoClient.editar(id, request);
    }

    public DocumentoResponse buscarNoModuloEditais(Long id) {
        return documentoClient.buscar(id);
    }

    public void deletarNoModuloEditais(Long id) {
        documentoClient.deletar(id);
    }
}