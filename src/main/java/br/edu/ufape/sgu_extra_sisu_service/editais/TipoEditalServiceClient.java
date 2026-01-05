package br.edu.ufape.sgu_extra_sisu_service.editais;

import br.edu.ufape.sgu_extra_sisu_service.controller.request.TipoEditalRequest;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.TipoEditalResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "sgu-editais-service", path = "/tipo-edital")
public interface TipoEditalServiceClient {

    @PostMapping
    TipoEditalResponse salvar(@RequestBody TipoEditalRequest request);

    @GetMapping("/{id}")
    TipoEditalResponse buscar(@PathVariable("id") Long id);

    @GetMapping
    Page<TipoEditalResponse> listar(Pageable pageable);

    @PatchMapping("/{id}")
    TipoEditalResponse editar(@PathVariable("id") Long id, @RequestBody TipoEditalRequest request);

    @DeleteMapping("/{id}")
    void deletar(@PathVariable("id") Long id);
}