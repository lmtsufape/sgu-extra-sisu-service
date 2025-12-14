package br.edu.ufape.sgu_extra_sisu_service.service;

import br.edu.ufape.sgu_extra_sisu_service.model.EditalExtraSisu;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.querydsl.core.types.Predicate;

public interface EditalExtraSisuService {
    Page<EditalExtraSisu> listarTodos(Predicate filtro, Pageable pageable);
    EditalExtraSisu buscarPorId(Long id);
    EditalExtraSisu salvar(EditalExtraSisu edital);
    EditalExtraSisu atualizar(Long id, EditalExtraSisu edital);
    void deletar(Long id);
}