package br.edu.ufape.sgu_extra_sisu_service.controller;

import br.edu.ufape.sgu_extra_sisu_service.controller.response.PublicacaoResponse;
import br.edu.ufape.sgu_extra_sisu_service.model.Publicacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.querydsl.core.types.Predicate;

import br.edu.ufape.sgu_extra_sisu_service.controller.request.ModalidadeRequest;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.ModalidadeResponse;
import br.edu.ufape.sgu_extra_sisu_service.model.Modalidade;
import br.edu.ufape.sgu_extra_sisu_service.service.ModalidadeService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/modalidade")
public class ModalidadeController {

    @Autowired
    private ModalidadeService modalidadeService;

    @PostMapping("/create")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ModalidadeResponse create(@Valid @RequestBody ModalidadeRequest request) {
        Modalidade novaModalidade = request.toModel();
        return new ModalidadeResponse(modalidadeService.create(novaModalidade));
    }

    @GetMapping("/list")
    @ResponseStatus(code = HttpStatus.OK)
    public Page<ModalidadeResponse> list(@QuerydslPredicate(root = Modalidade.class) Predicate filtro, Pageable pageable) {
        // Certifique-se de atualizar o método 'list' ou 'listarTodos' no Service para aceitar (Predicate, Pageable)
        return modalidadeService.list(filtro, pageable)
                .map(ModalidadeResponse::new);
    }

    @GetMapping("/{id}")
    public ModalidadeResponse localizarPorId(@PathVariable("id") Long id) {
        Modalidade m = modalidadeService.buscarPorId(id);
        return new ModalidadeResponse(m);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ModalidadeResponse update(@PathVariable Long id, @Valid @RequestBody ModalidadeRequest request) {
        Modalidade modalidadeAtualizada = request.toModel();
        return new ModalidadeResponse(modalidadeService.update(id, modalidadeAtualizada));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        modalidadeService.delete(id);
    }
}