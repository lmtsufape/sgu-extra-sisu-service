package br.edu.ufape.sgu_extra_sisu_service.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ufape.sgu_extra_sisu_service.model.Modalidade;
import br.edu.ufape.sgu_extra_sisu_service.repository.ModalidadeRepository;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class ModalidadeServiceIntegrationTest {

    @Autowired
    private ModalidadeService service;

    @Autowired
    private ModalidadeRepository repository;

    @Test
    void devePersistirModalidadeNoBancoReal() {
        Modalidade modalidade = new Modalidade();
        modalidade.setNome("Integração Teste");
        modalidade.setDescricao("Descrição de integração");

        Modalidade salvo = service.create(modalidade);

        assertNotNull(salvo.getId());
        assertEquals(1, repository.count());

        Modalidade doBanco = repository.findById(salvo.getId()).orElse(null);
        assertNotNull(doBanco);
        assertEquals("Integração Teste", doBanco.getNome());
    }

    @Test
    void deveDeletarModalidadeDoBancoReal() {
        Modalidade m = new Modalidade();
        m.setNome("Para Deletar");
        m = repository.save(m);

        service.delete(m.getId());

        assertFalse(repository.existsById(m.getId()));
    }
}