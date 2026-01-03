package br.edu.ufape.sgu_extra_sisu_service.editais;

import br.edu.ufape.sgu_extra_sisu_service.comunicacao.dto.edital.EditalExtraSisuDTO;
import br.edu.ufape.sgu_extra_sisu_service.model.EditalExtraSisu;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "sgu-editais-service")
public interface EditalExtraSisuServiceClient {

    @GetMapping("/editais/{id}")
    EditalExtraSisuDTO buscarPorId(@PathVariable("id") Long id);

    @GetMapping("/edital-extra-sisu")
    Page<EditalExtraSisu> listarTodos(@RequestParam("page") int page, @RequestParam("size") int size);

    @GetMapping("/editais")
    List<EditalExtraSisuDTO> buscarTodosEditaisExternos();
}