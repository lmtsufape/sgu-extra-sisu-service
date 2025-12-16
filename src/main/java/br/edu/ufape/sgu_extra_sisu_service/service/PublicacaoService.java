package br.edu.ufape.sgu_extra_sisu_service.service;

import br.edu.ufape.sgu_extra_sisu_service.model.Publicacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.querydsl.core.types.Predicate;

public interface PublicacaoService {
    Page<Publicacao> listarTodos(Predicate filtro, Pageable pageable);
    Publicacao buscarPorId(Long id);
    Publicacao salvar(Publicacao publicacao);
    Publicacao atualizar(Long id, Publicacao publicacao);
    void deletar(Long id);
}
