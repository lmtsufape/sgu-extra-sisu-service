package br.edu.ufape.sgu_extra_sisu_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufape.sgu_extra_sisu_service.controller.response.HistoricoEtapaInscricaoResponse;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.PageResponse;
import br.edu.ufape.sgu_extra_sisu_service.service.interfaces.HistoricoEtapaInscricaoHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/historico-etapa-inscricao")
@Tag(name = "Histórico de Etapas", description = "Visualiza o histórico de mudanças nas inscrições")
@RequiredArgsConstructor
public class HistoricoEtapaInscricaoController {

    private final HistoricoEtapaInscricaoHandler editalHistoricoEtapaHandler;

    @GetMapping("/{id}")
    @Operation(summary = "Buscar registro de histórico por ID")
    public ResponseEntity<HistoricoEtapaInscricaoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(editalHistoricoEtapaHandler.buscarPorId(id));
    }

    @GetMapping
    @Operation(summary = "Listar todos os históricos (Paginado)")
    public ResponseEntity<PageResponse<HistoricoEtapaInscricaoResponse>> listar() {
        return ResponseEntity.ok(editalHistoricoEtapaHandler.listar());
    }
}