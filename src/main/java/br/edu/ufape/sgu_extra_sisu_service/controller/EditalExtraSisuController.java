package br.edu.ufape.sgu_extra_sisu_service.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;

import br.edu.ufape.sgu_extra_sisu_service.controller.request.EditalRequest;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.EditalResponse;
import br.edu.ufape.sgu_extra_sisu_service.model.EditalExtraSisu;
import br.edu.ufape.sgu_extra_sisu_service.service.interfaces.EditalExtraSisuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/editais")
@RequiredArgsConstructor
public class EditalExtraSisuController {

    private final EditalExtraSisuService extraSisuService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public EditalResponse criarEdital(@RequestBody @Valid EditalRequest novo) {
        EditalExtraSisu edital = novo.toModel();
        edital = extraSisuService.salvar(edital);

        return new EditalResponse(edital);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EditalResponse> localizarPorId(@PathVariable("id") Long id) {
        EditalExtraSisu edital = extraSisuService.buscarPorId(id);

        return ResponseEntity.ok(new EditalResponse(edital));
    }

    @GetMapping
    public Page<EditalResponse> listarTodos(
            @QuerydslPredicate(root = EditalExtraSisu.class) Predicate filtro,
            Pageable pageable) {

        return extraSisuService.listarTodos(filtro, pageable)
                .map(EditalResponse::new);
    }

    @PutMapping("/{id}")
    public EditalResponse atualizar(@PathVariable Long id, @RequestBody @Valid EditalRequest requisicao) {
        EditalExtraSisu editalAtualizado = requisicao.toModel();
        EditalExtraSisu salvo = extraSisuService.atualizar(id, editalAtualizado);
        
        return new EditalResponse(salvo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        extraSisuService.deletar(id);
    }
}