package br.edu.ufape.sgu_extra_sisu_service.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.edu.ufape.sgu_extra_sisu_service.model.Modalidade;
import br.edu.ufape.sgu_extra_sisu_service.repository.ModalidadeRepository;

@ExtendWith(MockitoExtension.class)
class ModalidadeServiceTest {

    @InjectMocks
    private ModalidadeService service;

    @Mock
    private ModalidadeRepository repository;

    private Modalidade modalidadeValida;

    @BeforeEach
    void setup() {
        modalidadeValida = new Modalidade();
        modalidadeValida.setId(1L);
        modalidadeValida.setNome("Ampla Concorrência");
        modalidadeValida.setDescricao("Vagas destinadas a todos os candidatos");
    }

    @Test
    void salvarModalidadeComSucesso() {
        when(repository.save(any(Modalidade.class))).thenReturn(modalidadeValida);

        Modalidade salvo = service.create(modalidadeValida);

        assertNotNull(salvo);
        assertEquals("Ampla Concorrência", salvo.getNome());
        verify(repository, times(1)).save(any(Modalidade.class));
    }

    @Test
    void buscarPorIdQuandoIdExistir() {
        when(repository.findById(1L)).thenReturn(Optional.of(modalidadeValida));

        Modalidade encontrado = service.buscarPorId(1L);

        assertNotNull(encontrado);
        assertEquals(1L, encontrado.getId());
    }

    @Test
    void lancarExcecaoQuandoIdNaoExistir() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            service.buscarPorId(99L);
        });

        assertEquals("Modalidade não encontrada com id: 99", ex.getMessage());
    }

    @Test
    void atualizarComSucesso() {
        Modalidade dadosNovos = new Modalidade();
        dadosNovos.setNome("Nome Alterado");
        dadosNovos.setDescricao("Nova Descrição");

        when(repository.findById(1L)).thenReturn(Optional.of(modalidadeValida));
        when(repository.save(any(Modalidade.class))).thenReturn(modalidadeValida);

        Modalidade resultado = service.update(1L, dadosNovos);

        assertNotNull(resultado);
        verify(repository).save(argThat(m ->
                m.getNome().equals("Nome Alterado") && m.getDescricao().equals("Nova Descrição")
        ));
    }
}
