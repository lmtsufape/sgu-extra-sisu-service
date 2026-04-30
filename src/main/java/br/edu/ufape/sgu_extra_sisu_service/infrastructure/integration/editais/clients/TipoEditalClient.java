package br.edu.ufape.sgu_extra_sisu_service.infrastructure.integration.editais.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.ufape.sgu_extra_sisu_service.infrastructure.integration.editais.dto.TipoEditalRequest;
import br.edu.ufape.sgu_extra_sisu_service.infrastructure.integration.editais.dto.TipoEditalResponse;



@FeignClient(name = "sgu-editais-service", contextId="tipoEditalClient", path = "/tipo-edital")
public interface TipoEditalClient {

    @PostMapping
    TipoEditalResponse salvar(@RequestBody TipoEditalRequest request);

    @GetMapping("/{id}")
    TipoEditalResponse buscar(@PathVariable("id") Long id);

    @GetMapping
    Page<TipoEditalResponse> listar(
        @RequestParam int page,
        @RequestParam int size,
        @RequestParam(defaultValue = "nome,asc") String sort
    );

    @PatchMapping("/{id}")
    TipoEditalResponse atualizar(@PathVariable("id") Long id, @RequestBody TipoEditalRequest request);

    @DeleteMapping("/{id}")
    void deletar(@PathVariable("id") Long id);
}