package br.edu.ufape.sgu_extra_sisu_service.controller;

import br.edu.ufape.sgu_extra_sisu_service.controller.request.EtapaRequest;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.EtapaResponse;
import br.edu.ufape.sgu_extra_sisu_service.service.EtapaServiceHandler;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/etapas")
@RequiredArgsConstructor
public class EtapaController {

    private final EtapaServiceHandler etapaHandler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EtapaResponse criarEtapa(@RequestBody @Valid EtapaRequest request) {
        return etapaHandler.salvarNoModuloEditais(request);
    }

    @GetMapping("/{id}")
    public EtapaResponse buscarPorId(@PathVariable Long id) {
        EtapaResponse response = etapaHandler.buscarNoModuloEditais(id);
        if (response == null) {
            throw new RuntimeException("Etapa não encontrada no módulo de Editais");
        }
        return response;
    }
}