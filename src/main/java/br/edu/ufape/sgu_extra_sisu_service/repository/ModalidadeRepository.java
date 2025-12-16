package br.edu.ufape.sgu_extra_sisu_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.StringPath;

import br.edu.ufape.sgu_extra_sisu_service.model.Modalidade;
import br.edu.ufape.sgu_extra_sisu_service.model.QModalidade;

@Repository
public interface ModalidadeRepository extends JpaRepository<Modalidade, Long>,
        QuerydslPredicateExecutor<Modalidade>,
        QuerydslBinderCustomizer<QModalidade> {

    @Override
    default void customize(QuerydslBindings bindings, QModalidade root) {
        bindings.bind(String.class)
                .first((StringPath path, String value) -> path.containsIgnoreCase(value));
    }
}