package br.edu.ufape.sgu_extra_sisu_service.controller;

import br.edu.ufape.sgu_extra_sisu_service.controller.request.AvaliarIsencaoRequest;
import br.edu.ufape.sgu_extra_sisu_service.controller.request.IsencaoRequest;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.IsencaoResponse;
import br.edu.ufape.sgu_extra_sisu_service.fachada.Fachada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/isencoes")
public class IsencaoController {
    @Autowired
    private Fachada fachada;

    @PostMapping
    public IsencaoResponse solicitar(@RequestBody IsencaoRequest req) {
        Long idDoUsuarioLogado = req.getUsuarioId();
        return new IsencaoResponse(fachada.salvarIsencao(1L, req));
    }

    @PatchMapping("/{id}/avaliar")
    public IsencaoResponse avaliar(@PathVariable Long id, @RequestBody AvaliarIsencaoRequest req) {
        return new IsencaoResponse(fachada.avaliarIsencao(id, req));
    }
}
