package br.edu.ufape.sgu_extra_sisu_service.controller;

import br.edu.ufape.sgu_extra_sisu_service.controller.request.EtapaRequest;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.EtapaResponse;
import br.edu.ufape.sgu_extra_sisu_service.fachada.Fachada;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/etapas")
public class EtapaController {

    @Autowired
    private Fachada fachada;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EtapaResponse criarEtapa(@RequestBody @Valid EtapaRequest request) {
        return fachada.salvarEtapa(request);
    }

    @GetMapping("/{id}")
    public EtapaResponse buscarPorId(@PathVariable Long id) {
        EtapaResponse response = fachada.buscarEtapa(id);
        if (response == null) {
            throw new RuntimeException("Etapa não encontrada no módulo de Editais");
        }
        return response;
    }
}