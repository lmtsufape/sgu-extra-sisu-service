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
import br.edu.ufape.sgu_extra_sisu_service.model.Publicacao;
import br.edu.ufape.sgu_extra_sisu_service.repository.EditalExtraSisuRepository;
import br.edu.ufape.sgu_extra_sisu_service.repository.PublicacaoRepository;

@ExtendWith(MockitoExtension.class)
class PublicacaoServiceTest {

    @InjectMocks
    private PublicacaoServiceImpl service;

    @Mock
    private PublicacaoRepository repository;

    @Mock
    private EditalExtraSisuRepository editalRepository;

    private Publicacao publicacaoValida;
    private EditalExtraSisu edital;

    @BeforeEach
    void setup() {
        edital = new EditalExtraSisu();
        edital.setId(1L);

        publicacaoValida = new Publicacao();
        publicacaoValida.setId(1L);
        publicacaoValida.setNome("Pub Teste");
        publicacaoValida.setStatus("ATIVA");
        publicacaoValida.setPdf("link.pdf");
        publicacaoValida.setTexto("texto");
        publicacaoValida.setDataPublicacao(LocalDateTime.now());
        publicacaoValida.setEdital(edital);
    }

    @Test
    void salvarPublicacaoComSucesso() {
        when(editalRepository.findById(1L)).thenReturn(Optional.of(edital));
        when(repository.save(any(Publicacao.class))).thenReturn(publicacaoValida);

        Publicacao salvo = service.salvar(publicacaoValida);

        assertNotNull(salvo);
        assertEquals("Pub Teste", salvo.getNome());
        verify(repository, times(1)).save(any(Publicacao.class));
    }

    @Test
    void lancarExcecaoQuandoEditalNaoInformado() {
        Publicacao p = new Publicacao();
        p.setNome("Sem Edital");

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            service.salvar(p);
        });

        assertEquals("Edital associado não informado", ex.getMessage());
        verify(repository, never()).save(any());
    }

    @Test
    void buscarPorIdQuandoIdExistir() {
        when(repository.findById(1L)).thenReturn(Optional.of(publicacaoValida));

        Publicacao encontrado = service.buscarPorId(1L);

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

    @Test
    void atualizarComSucesso() {
        Publicacao atualizado = new Publicacao();
        atualizado.setNome("Atualizada");
        atualizado.setStatus("ATIVA");
        atualizado.setPdf("a.pdf");
        atualizado.setTexto("t");
        atualizado.setDataPublicacao(LocalDateTime.now());
        EditalExtraSisu novoEdital = new EditalExtraSisu();
        novoEdital.setId(1L);
        atualizado.setEdital(novoEdital);

        when(repository.findById(1L)).thenReturn(Optional.of(publicacaoValida));
        when(editalRepository.findById(1L)).thenReturn(Optional.of(novoEdital));
        when(repository.save(any(Publicacao.class))).thenReturn(publicacaoValida);

        Publicacao salvo = service.atualizar(1L, atualizado);

        assertNotNull(salvo);
        verify(repository, times(1)).save(any(Publicacao.class));
    }
}
