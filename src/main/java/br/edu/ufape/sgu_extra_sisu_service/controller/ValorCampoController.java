package br.edu.ufape.sgu_extra_sisu_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufape.sgu_extra_sisu_service.controller.request.ValorCampoRequest;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.PageResponse;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.ValorCampoResponse;
import br.edu.ufape.sgu_extra_sisu_service.service.interfaces.ValorCampoHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/valor-campo")
@Tag(name = "Valor Campo", description = "Gerencia as respostas dos campos personalizados")
@RequiredArgsConstructor
public class ValorCampoController {

    private final ValorCampoHandler valorCampoHandler;

    @PostMapping
    @Operation(summary = "Salvar resposta de campo")
    public ResponseEntity<ValorCampoResponse> salvar(@RequestBody ValorCampoRequest request) {
        return new ResponseEntity<>(valorCampoHandler.salvar(request), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Atualizar resposta")
    public ResponseEntity<ValorCampoResponse> editar(@PathVariable Long id, @RequestBody ValorCampoRequest request) {
        return ResponseEntity.ok(valorCampoHandler.atualizar(id, request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar resposta por ID")
    public ResponseEntity<ValorCampoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(valorCampoHandler.buscarPorId(id));
    }

    @GetMapping
    @Operation(summary = "Listar todas as respostas")
    public ResponseEntity<PageResponse<ValorCampoResponse>> listar() {
        return ResponseEntity.ok(valorCampoHandler.listar());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar resposta")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        valorCampoHandler.deletar(id);
        return ResponseEntity.noContent().build();
    }
}