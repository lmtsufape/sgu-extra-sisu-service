package br.edu.ufape.sgu_extra_sisu_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.querydsl.core.types.Predicate;

import br.edu.ufape.sgu_extra_sisu_service.controller.request.PublicacaoRequest;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.PublicacaoResponse;
import br.edu.ufape.sgu_extra_sisu_service.model.Publicacao;
import br.edu.ufape.sgu_extra_sisu_service.service.PublicacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/publicacoes")
@RequiredArgsConstructor
public class PublicacaoController {

    @Autowired
    private final PublicacaoService service;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public PublicacaoResponse criar(@RequestBody @Valid PublicacaoRequest novo) {
        Publicacao p = novo.toModel();
        p = service.salvar(p);
        return new PublicacaoResponse(p);
    }

    @GetMapping("/{id}")
    public PublicacaoResponse localizarPorId(@PathVariable("id") Long id) {
        Publicacao p = service.buscarPorId(id);
        return new PublicacaoResponse(p);
    }

    @GetMapping
    public Page<PublicacaoResponse> listarTodos(@QuerydslPredicate(root = Publicacao.class) Predicate filtro,
            Pageable pageable) {
        return service.listarTodos(filtro, pageable).map(PublicacaoResponse::new);
    }

    @PutMapping("/{id}")
    public PublicacaoResponse atualizar(@PathVariable Long id, @RequestBody @Valid PublicacaoRequest requisicao) {
        Publicacao atualizado = requisicao.toModel();
        Publicacao salvo = service.atualizar(id, atualizado);
        return new PublicacaoResponse(salvo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
