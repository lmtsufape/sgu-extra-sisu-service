package br.edu.ufape.sgu_extra_sisu_service.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;


import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.edu.ufape.sgu_extra_sisu_service.controller.request.PublicacaoRequest;
import br.edu.ufape.sgu_extra_sisu_service.model.EditalExtraSisu;
import br.edu.ufape.sgu_extra_sisu_service.model.Publicacao;
import br.edu.ufape.sgu_extra_sisu_service.service.PublicacaoService;

@WebMvcTest(PublicacaoController.class)
class PublicacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PublicacaoService service;

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    private PublicacaoRequest requestValido;
    private Publicacao publicacaoSalva;

    @BeforeEach
    void setup() {
        requestValido = new PublicacaoRequest();
        requestValido.setNome("Publicação Teste");
        requestValido.setStatus("ATIVA");
        requestValido.setPdf("pub.pdf");
        requestValido.setTexto("Texto de exemplo");
        requestValido.setDataPublicacao(LocalDateTime.now());
        requestValido.setEditalId(1L);

        EditalExtraSisu edital = new EditalExtraSisu();
        edital.setId(1L);

        publicacaoSalva = new Publicacao();
        publicacaoSalva.setId(1L);
        publicacaoSalva.setNome(requestValido.getNome());
        publicacaoSalva.setStatus(requestValido.getStatus());
        publicacaoSalva.setPdf(requestValido.getPdf());
        publicacaoSalva.setTexto(requestValido.getTexto());
        publicacaoSalva.setDataPublicacao(requestValido.getDataPublicacao());
        publicacaoSalva.setEdital(edital);
    }

    @Test
    void criarPublicacaoComSucesso() throws Exception {
        when(service.salvar(any(Publicacao.class))).thenReturn(publicacaoSalva);

        mockMvc.perform(post("/publicacoes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestValido)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Publicação Teste"));
    }

    @Test
    void publicacaoFaltandoDadosObrigatorios() throws Exception {
        PublicacaoRequest requestInvalido = new PublicacaoRequest();

        mockMvc.perform(post("/publicacoes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestInvalido)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Erro de validação"));
    }

    @Test
    void buscarPorIdExistente() throws Exception {
        when(service.buscarPorId(1L)).thenReturn(publicacaoSalva);

        mockMvc.perform(get("/publicacoes/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Publicação Teste"));
    }

    @Test
    void buscarIdInexistente() throws Exception {
        when(service.buscarPorId(99L)).thenThrow(new RuntimeException("Publicação não encontrada"));

        mockMvc.perform(get("/publicacoes/{id}", 99L))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Erro de Regra de Negócio"));
    }

    @Test
    void listarTodosPaginado() throws Exception {
        PageImpl<Publicacao> page = new PageImpl<>(List.of(publicacaoSalva));
        when(service.listarTodos(any(), any())).thenReturn(page);

        mockMvc.perform(get("/publicacoes")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].nome").value("Publicação Teste"));
    }

    @Test
    void atualizarComSucesso() throws Exception {
        when(service.atualizar(eq(1L), any(Publicacao.class))).thenReturn(publicacaoSalva);

        mockMvc.perform(put("/publicacoes/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestValido)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Publicação Teste"));
    }

    @Test
    void deletarComSucesso() throws Exception {
        doNothing().when(service).deletar(1L);

        mockMvc.perform(delete("/publicacoes/{id}", 1L))
                .andExpect(status().isNoContent());
    }
}
