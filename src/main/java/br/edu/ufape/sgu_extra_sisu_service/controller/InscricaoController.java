package br.edu.ufape.sgu_extra_sisu_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;
import br.edu.ufape.sgu_extra_sisu_service.controller.request.StatusInscricaoPatchRequest;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.InscricaoDetalhadaResponse;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.InscricaoResponse;
import br.edu.ufape.sgu_extra_sisu_service.service.interfaces.EditalAdminHandler;
import br.edu.ufape.sgu_extra_sisu_service.service.interfaces.EditalInscricaoHandler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/inscricoes") 
@Tag(name = "Inscrições Externas", description = "Integração com Sistema de Editais")
@RequiredArgsConstructor
public class InscricaoController {

    private final EditalInscricaoHandler editalInscricaoHandler;
    private final EditalAdminHandler editalAdminHandler;



    @PostMapping("/edital/{editalId}")
    public ResponseEntity<InscricaoResponse> realizarInscricao(@PathVariable Long editalId) {
        InscricaoResponse response = editalInscricaoHandler.realizarInscricao(editalId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<InscricaoResponse>> listarMinhasInscricoes() {
        List<InscricaoResponse> lista = editalInscricaoHandler.listarMinhasInscricoes();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InscricaoResponse> buscarPorId(@PathVariable Long id) {
        InscricaoResponse response = editalInscricaoHandler.buscarInscricaoPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/detalhes")
    public ResponseEntity<InscricaoDetalhadaResponse> buscarDetalhesPorId(@PathVariable Long id) {
        InscricaoDetalhadaResponse response = editalAdminHandler.buscarDetalhesInscricao(id);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<InscricaoResponse> atualizarStatus(
            @PathVariable Long id,
            @RequestBody StatusInscricaoPatchRequest request) { 
        
        InscricaoResponse response = editalInscricaoHandler.atualizarStatusInscricao(
                id, 
                request.getStatusId(), 
                request.getObservacao()
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        editalInscricaoHandler.deletarInscricao(id);
        return ResponseEntity.noContent().build();
    }
}
