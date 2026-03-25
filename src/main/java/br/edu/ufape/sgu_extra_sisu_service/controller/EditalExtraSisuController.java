package br.edu.ufape.sgu_extra_sisu_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.querydsl.core.types.Predicate;

import br.edu.ufape.sgu_extra_sisu_service.controller.request.EditalRequest;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.EditalResponse;
import br.edu.ufape.sgu_extra_sisu_service.model.EditalExtraSisu;
import br.edu.ufape.sgu_extra_sisu_service.fachada.Fachada;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/editais")
public class EditalExtraSisuController {

    @Autowired
    private Fachada fachada;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public EditalResponse criarEdital(@RequestBody @Valid EditalRequest novo) {
        // Passa o request completo para que a Fachada consiga os IDs externos
        EditalExtraSisu edital = novo.toModel();
        edital = fachada.salvarEditalExtraSisu(edital);
        return new EditalResponse(edital);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EditalResponse> localizarPorId(@PathVariable("id") Long id) {
        EditalExtraSisu edital = fachada.buscarEditalExtraSisu(id);
        return ResponseEntity.ok(new EditalResponse(edital));
    }

    @GetMapping
    public Page<EditalResponse> listarTodos(
            @QuerydslPredicate(root = EditalExtraSisu.class) Predicate filtro,
            Pageable pageable) {

        return fachada.listarEditaisExtraSisu(filtro, pageable)
                .map(EditalResponse::new);
    }

    @PutMapping("/{id}")
    public EditalResponse atualizar(@PathVariable Long id, @RequestBody @Valid EditalRequest requisicao) {
        EditalExtraSisu editalAtualizado = requisicao.toModel();
        EditalExtraSisu salvo = fachada.atualizarEditalExtraSisu(id, editalAtualizado);
        return new EditalResponse(salvo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        fachada.deletarEditalExtraSisu(id);
    }
}