package br.edu.ufape.sgu_extra_sisu_service.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ufape.sgu_extra_sisu_service.model.EditalExtraSisu;
import br.edu.ufape.sgu_extra_sisu_service.repository.EditalExtraSisuRepository;

@SpringBootTest 
@ActiveProfiles("test") 
@Transactional 
class EditalExtraSisuServiceIntegrationTest {

    @Autowired 
    private EditalExtraSisuService service;

    @Autowired 
    private EditalExtraSisuRepository repository;

    @Test
    void salvarEditalNoBancoDeDados() {
        EditalExtraSisu novoEdital = new EditalExtraSisu();
        novoEdital.setNome("Edital Real 2025");
        novoEdital.setPdf("arquivo.pdf");
        novoEdital.setDataInscricao(LocalDateTime.now());
        novoEdital.setDataFinalizacao(LocalDateTime.now().plusDays(5));

        EditalExtraSisu salvo = service.salvar(novoEdital);

        assertNotNull(salvo.getId()); 
        assertEquals(1, repository.count()); 
        
        EditalExtraSisu doBanco = repository.findById(salvo.getId()).get();
        assertEquals("Edital Real 2025", doBanco.getNome());
    }

    @Test
    void naoDeveSalvarEditalComDatasInvalidas() {
        EditalExtraSisu editalInvalido = new EditalExtraSisu();
        editalInvalido.setNome("Edital Falho");
        editalInvalido.setDataInscricao(LocalDateTime.now());
        editalInvalido.setDataFinalizacao(LocalDateTime.now().minusDays(1));

        assertThrows(RuntimeException.class, () -> {
            service.salvar(editalInvalido);
        });

        assertEquals(0, repository.count());
    }
}