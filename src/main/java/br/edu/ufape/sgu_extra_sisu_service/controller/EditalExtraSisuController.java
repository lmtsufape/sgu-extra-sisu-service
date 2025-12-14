package br.edu.ufape.sgu_extra_sisu_service.controller;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.querydsl.core.types.Predicate;

import br.edu.ufape.sgu_extra_sisu_service.controller.request.EditalRequest;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.EditalResponse;
import br.edu.ufape.sgu_extra_sisu_service.model.EditalExtraSisu;
import br.edu.ufape.sgu_extra_sisu_service.service.EditalExtraSisuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor; 

@RestController
@RequestMapping("/editais") 
@RequiredArgsConstructor 
public class EditalExtraSisuController {

    @Autowired
    private final EditalExtraSisuService service;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED) 
    public EditalResponse criarEdital(@RequestBody @Valid EditalRequest novo) {
        EditalExtraSisu edital = novo.toModel();
        edital = service.salvar(edital);
        return new EditalResponse(edital);
    }

    @GetMapping("/{id}")
    public EditalResponse localizarPorId(@PathVariable("id") Long id) {
        EditalExtraSisu edital = service.buscarPorId(id);
        return new EditalResponse(edital);
    }

    @GetMapping
    public Page<EditalResponse> listarTodos(
            @QuerydslPredicate(root = EditalExtraSisu.class) Predicate filtro,
            Pageable pageable) {
        
        return service.listarTodos(filtro, pageable) 
                .map(EditalResponse::new); 
    }

    @PutMapping("/{id}")
    public EditalResponse atualizar(@PathVariable Long id, @RequestBody @Valid EditalRequest requisicao) {
        EditalExtraSisu editalAtualizado = requisicao.toModel();
        EditalExtraSisu salvo = service.atualizar(id, editalAtualizado);
        return new EditalResponse(salvo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) 
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}