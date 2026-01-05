package br.edu.ufape.sgu_extra_sisu_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;
import br.edu.ufape.sgu_extra_sisu_service.controller.request.StatusInscricaoPatchRequest;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.InscricaoResponse;
import br.edu.ufape.sgu_extra_sisu_service.fachada.Fachada;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/inscricoes") 
@RequiredArgsConstructor
@Tag(name = "Inscrições Externas", description = "Integração com Sistema de Editais")
public class InscricaoController {

    private final Fachada fachada;

    @PostMapping("/edital/{editalId}")
    public ResponseEntity<InscricaoResponse> realizarInscricao(@PathVariable Long editalId) {
        InscricaoResponse response = fachada.realizarInscricaoEmEdital(editalId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<InscricaoResponse>> listarMinhasInscricoes() {
        List<InscricaoResponse> lista = fachada.listarMinhasInscricoesExternas();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InscricaoResponse> buscarPorId(@PathVariable Long id) {
        InscricaoResponse response = fachada.buscarInscricaoExternaPorId(id);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<InscricaoResponse> atualizarStatus(
            @PathVariable Long id,
            @RequestBody StatusInscricaoPatchRequest request) { 
        
        InscricaoResponse response = fachada.atualizarStatusInscricaoExterna(
                id, 
                request.getStatusId(), 
                request.getObservacao()
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        fachada.deletarInscricaoExterna(id);
        return ResponseEntity.noContent().build();
    }
}
