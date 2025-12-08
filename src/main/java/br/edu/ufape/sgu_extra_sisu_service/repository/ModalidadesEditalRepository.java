package br.edu.ufape.sgu_extra_sisu_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufape.sgu_extra_sisu_service.model.ModalidadesEdital;

@Repository
public interface ModalidadesEditalRepository extends JpaRepository<ModalidadesEdital, Long> {
    
}
