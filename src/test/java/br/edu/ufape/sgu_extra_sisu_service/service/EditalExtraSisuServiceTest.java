package br.edu.ufape.sgu_extra_sisu_service.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.edu.ufape.sgu_extra_sisu_service.model.EditalExtraSisu;
import br.edu.ufape.sgu_extra_sisu_service.repository.EditalExtraSisuRepository;


@ExtendWith(MockitoExtension.class) 
class EditalExtraSisuServiceTest {

    @InjectMocks 
    private EditalExtraSisuServiceImpl service;

    @Mock 
    private EditalExtraSisuRepository repository;

    private EditalExtraSisu editalValido;

    @BeforeEach
    void setup() {
        editalValido = new EditalExtraSisu();
        editalValido.setId(1L);
        editalValido.setNome("Edital Teste 2025");
        editalValido.setPdf("link-do-pdf.com");
        editalValido.setDataInscricao(LocalDateTime.now()); 
        editalValido.setDataFinalizacao(LocalDateTime.now().plusDays(10));
    }

    @Test
    void salvarEditalComDatasValidas() {

        when(repository.save(any(EditalExtraSisu.class))).thenReturn(editalValido);

        EditalExtraSisu salvo = service.salvar(editalValido);

        assertNotNull(salvo);
        assertEquals("Edital Teste 2025", salvo.getNome());
        
        verify(repository, times(1)).save(any(EditalExtraSisu.class));
    }

    @Test
    void lancarExcecaoQuandoDataFinalForAnteriorADataInicial() {

        editalValido.setDataInscricao(LocalDateTime.now());
        editalValido.setDataFinalizacao(LocalDateTime.now().minusDays(1)); 

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            service.salvar(editalValido);
        });

        assertEquals("A data final não pode ser anterior à data de inscrição.", ex.getMessage());

        verify(repository, never()).save(any());
    }

    @Test
    void buscarPorIdQuandoIdExistir() {

        when(repository.findById(1L)).thenReturn(Optional.of(editalValido));

        EditalExtraSisu encontrado = service.buscarPorId(1L);

        assertNotNull(encontrado);
        assertEquals(1L, encontrado.getId());
    }

    @Test
    void lancarExcecaoQuandoIdNaoExistir() {

        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            service.buscarPorId(99L);
        });
    }
}