package br.edu.ufape.sgu_extra_sisu_service.repository;

import br.edu.ufape.sgu_extra_sisu_service.model.EditalExtraSisu;
import br.edu.ufape.sgu_extra_sisu_service.model.Isencao;
import br.edu.ufape.sgu_extra_sisu_service.model.QEditalExtraSisu;
import br.edu.ufape.sgu_extra_sisu_service.model.QIsencao;
import br.edu.ufape.sgu_extra_sisu_service.model.enums.StatusIsencao;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

public interface IsencaoRepository extends JpaRepository<Isencao, Long>,
    QuerydslPredicateExecutor<Isencao>,
            QuerydslBinderCustomizer<QIsencao> {
        @Override
        default void customize(QuerydslBindings bindings, QIsencao root) {

            bindings.bind(String.class)
                    .first((StringPath path, String value) -> path.containsIgnoreCase(value));
        }

    boolean existsByUsuarioIdAndEditalIdAndModalidadeIdAndStatus(
            Long usuarioId, Long editalId, Long modalidadeId, StatusIsencao status);

}
