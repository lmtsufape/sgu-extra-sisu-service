package br.edu.ufape.sgu_extra_sisu_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufape.sgu_extra_sisu_service.model.Modalidade;

@Repository
public interface ModalidadeRepository extends JpaRepository<Modalidade, Long> {

    
}
