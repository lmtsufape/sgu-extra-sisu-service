package br.edu.ufape.sgu_extra_sisu_service.service;

import br.edu.ufape.sgu_extra_sisu_service.model.Modalidade;
import br.edu.ufape.sgu_extra_sisu_service.model.Publicacao;
import br.edu.ufape.sgu_extra_sisu_service.repository.ModalidadeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

@Service
public class ModalidadeService {

    @Autowired
    ModalidadeRepository modalidadeRepository;

    public Modalidade create(Modalidade modalidade) {
        return modalidadeRepository.save(modalidade);
    }

    public Modalidade update(Long id, Modalidade modalidade) {
        Modalidade modExistente = modalidadeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Modalidade não encontrada"));

        modExistente.setNome(modalidade.getNome());
        modExistente.setDescricao(modalidade.getDescricao());

        return modalidadeRepository.save(modExistente);
    }

    public void delete(Long id) {
        if (!modalidadeRepository.existsById(id)) {
            throw new RuntimeException("Modalidade não encontrada");
        }
        modalidadeRepository.deleteById(id);
    }

    public Modalidade buscarPorId(Long id) {
        return modalidadeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Modalidade não encontrada com id: " + id));
    }

    public Page<Modalidade> list(Predicate filtro, Pageable pageable) {
        return modalidadeRepository.findAll(filtro, pageable);
    }

    public Modalidade findById(Long id) {
        return modalidadeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Modalidade não encontrada"));
    }
}