package br.edu.ufape.sgu_extra_sisu_service.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufape.sgu_extra_sisu_service.client.TipoEditalServiceClient;
import br.edu.ufape.sgu_extra_sisu_service.controller.request.TipoEditalRequest;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.TipoEditalResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tipos-editais")
@RequiredArgsConstructor
public class TipoEditalExtraSisuController {
    
    private final TipoEditalServiceClient tipoEditalServiceClient;

    @PostMapping
    public TipoEditalResponse criar(@RequestBody TipoEditalRequest request) {
        return tipoEditalServiceClient.salvar(request);
    }

    @GetMapping("/{id}")
    public TipoEditalResponse buscarPorId(@PathVariable Long id) {
        return tipoEditalServiceClient.buscar(id);
    }

    @GetMapping
    public Page<TipoEditalResponse> listar(Pageable pageable) {
        return tipoEditalServiceClient.listar(pageable);
    }

    @PatchMapping("/{id}")
    public TipoEditalResponse editar(@PathVariable Long id, @RequestBody @Valid TipoEditalRequest request) {
        return tipoEditalServiceClient.editar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        tipoEditalServiceClient.deletar(id);
    }
}