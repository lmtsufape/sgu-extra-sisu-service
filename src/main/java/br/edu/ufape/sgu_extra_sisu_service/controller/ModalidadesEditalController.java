package br.edu.ufape.sgu_extra_sisu_service.controller;

import java.util.List;

import br.edu.ufape.sgu_extra_sisu_service.dto.request.ModalidadesEditalRequest;
import br.edu.ufape.sgu_extra_sisu_service.dto.response.ModalidadesEditalResponse;
import br.edu.ufape.sgu_extra_sisu_service.service.ModalidadesEditalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/modalidades-edital")
@RequiredArgsConstructor
public class ModalidadesEditalController {

    private final ModalidadesEditalService service;

    @PostMapping
    public ResponseEntity<ModalidadesEditalResponse> criar(
            @RequestBody @Valid ModalidadesEditalRequest request) {
        return ResponseEntity.ok(service.criar(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModalidadesEditalResponse> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<ModalidadesEditalResponse>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModalidadesEditalResponse> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid ModalidadesEditalRequest request) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
