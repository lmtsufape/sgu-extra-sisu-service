package br.edu.ufape.sgu_extra_sisu_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufape.sgu_extra_sisu_service.controller.request.AdminEditalRequest;
import br.edu.ufape.sgu_extra_sisu_service.controller.request.EtapaAdminRequest;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.AdminEditalDetalhadoResponse;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.EditalResponse;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.EtapaResponse;
import br.edu.ufape.sgu_extra_sisu_service.service.interfaces.EditalAdminHandler;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminEditalController {

    private final EditalAdminHandler editalAdminHandler;

    @GetMapping("/editais/{id}")
    public ResponseEntity<AdminEditalDetalhadoResponse> buscarEditalComEtapas(@PathVariable Long id) {
        return ResponseEntity.ok(editalAdminHandler.buscarEditalComEtapas(id));
    }

    @PatchMapping("/editais/{id}")
    public ResponseEntity<EditalResponse> editarEdital(@PathVariable Long id, @RequestBody AdminEditalRequest request) {
        return ResponseEntity.ok(editalAdminHandler.editarEdital(id, request));
    }

    @DeleteMapping("/editais/{id}")
    public ResponseEntity<Void> deletarEdital(@PathVariable Long id) {
        editalAdminHandler.deletarEdital(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/etapas/{id}")
    public ResponseEntity<EtapaResponse> editarEtapa(@PathVariable Long id, @RequestBody EtapaAdminRequest request) {
        return ResponseEntity.ok(editalAdminHandler.editarEtapa(id, request));
    }

    @DeleteMapping("/etapas/{id}")
    public ResponseEntity<Void> deletarEtapa(@PathVariable Long id) {
        editalAdminHandler.deletarEtapa(id);
        return ResponseEntity.noContent().build();
    }
}
