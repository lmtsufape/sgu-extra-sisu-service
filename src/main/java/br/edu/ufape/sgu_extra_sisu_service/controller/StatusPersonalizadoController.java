package br.edu.ufape.sgu_extra_sisu_service.controller;

import br.edu.ufape.sgu_extra_sisu_service.controller.request.StatusPersonalizadoRequest;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.StatusPersonalizadoResponse;

import br.edu.ufape.sgu_extra_sisu_service.fachada.Fachada;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/status-personalizado") 
@RequiredArgsConstructor
@Tag(name = "Status Personalizado (Integração)", description = "Gerencia Status do sistema de Editais")
public class StatusPersonalizadoController {

    private final Fachada fachada;

    @GetMapping
    @Operation(summary = "Listar Status", description = "Lista todos os status disponíveis no sistema de Editais.")
    public ResponseEntity<List<StatusPersonalizadoResponse>> listar() {
        return ResponseEntity.ok(fachada.listarStatusPersonalizados());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Status por ID")
    public ResponseEntity<StatusPersonalizadoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(fachada.buscarStatusPersonalizadoPorId(id));
    }

    @PostMapping
    @Operation(summary = "Criar Status no sistema externo")
    public ResponseEntity<StatusPersonalizadoResponse> salvar(@RequestBody StatusPersonalizadoRequest request) {
        return new ResponseEntity<>(fachada.salvarStatusPersonalizado(request), HttpStatus.CREATED);
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<StatusPersonalizadoResponse> editar(@PathVariable Long id, @RequestBody StatusPersonalizadoRequest request) {
        return ResponseEntity.ok(fachada.editarStatusPersonalizado(id, request));
    }
}