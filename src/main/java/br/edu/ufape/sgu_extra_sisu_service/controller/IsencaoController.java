package br.edu.ufape.sgu_extra_sisu_service.controller;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufape.sgu_extra_sisu_service.controller.request.AvaliarIsencaoRequest;
import br.edu.ufape.sgu_extra_sisu_service.controller.request.IsencaoRequest;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.IsencaoResponse;
import br.edu.ufape.sgu_extra_sisu_service.service.interfaces.IsencaoInterface;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/isencoes")
@RequiredArgsConstructor
public class IsencaoController {

    private final IsencaoInterface isencaoService;

    @PostMapping
    public IsencaoResponse solicitar(@RequestBody IsencaoRequest req) {
        Long idDoUsuarioLogado = req.getUsuarioId();
        return new IsencaoResponse(isencaoService.solicitar(1L, req));
    }

    @PatchMapping("/{id}/avaliar")
    public IsencaoResponse avaliar(@PathVariable Long id, @RequestBody AvaliarIsencaoRequest req) {
        return new IsencaoResponse(isencaoService.avaliar(id, req));
    }
}
