package br.edu.ufape.sgu_extra_sisu_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufape.sgu_extra_sisu_service.controller.request.StatusPersonalizadoRequest;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.StatusPersonalizadoResponse;
import br.edu.ufape.sgu_extra_sisu_service.service.interfaces.StatusPersonalizadoHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/status-personalizado") 
@Tag(name = "Status Personalizado (Integração)", description = "Gerencia Status do sistema de Editais")
@RequiredArgsConstructor
public class StatusPersonalizadoController {

    private final StatusPersonalizadoHandler statusPersonalizadoHandler;

    @GetMapping
    @Operation(summary = "Listar Status", description = "Lista todos os status disponíveis no sistema de Editais.")
    public ResponseEntity<List<StatusPersonalizadoResponse>> listar() {
        return ResponseEntity.ok(statusPersonalizadoHandler.listarStatusPersonalizados());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Status por ID")
    public ResponseEntity<StatusPersonalizadoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(statusPersonalizadoHandler.buscarStatusPersonalizadoPorId(id));
    }

    @PostMapping
    @Operation(summary = "Criar Status no sistema externo")
    public ResponseEntity<StatusPersonalizadoResponse> salvar(@RequestBody StatusPersonalizadoRequest request) {
        return new ResponseEntity<>(statusPersonalizadoHandler.criarStatusPersonalizado(request), HttpStatus.CREATED);
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<StatusPersonalizadoResponse> editar(@PathVariable Long id, @RequestBody StatusPersonalizadoRequest request) {
        return ResponseEntity.ok(statusPersonalizadoHandler.atualizarStatusPersonalizado(id, request));
    }
}