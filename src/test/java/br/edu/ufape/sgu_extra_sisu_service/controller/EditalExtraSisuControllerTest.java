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

import br.edu.ufape.sgu_extra_sisu_service.controller.request.EditalRequest;
import br.edu.ufape.sgu_extra_sisu_service.model.EditalExtraSisu;
import br.edu.ufape.sgu_extra_sisu_service.service.EditalExtraSisuService;

@WebMvcTest(EditalExtraSisuController.class)
class EditalExtraSisuControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EditalExtraSisuService service;

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    private EditalRequest requestValido;
    private EditalExtraSisu editalSalvo;

    @BeforeEach
    void setup() {
        requestValido = new EditalRequest();
        requestValido.setNome("Edital 2025.1");
        requestValido.setPdf("edital.pdf");
        requestValido.setDataInscricao(LocalDateTime.now().plusDays(1));
        requestValido.setDataFinalizacao(LocalDateTime.now().plusDays(10));

        editalSalvo = new EditalExtraSisu();
        editalSalvo.setId(1L);
        editalSalvo.setNome("Edital 2025.1");
        editalSalvo.setPdf("edital.pdf");
        editalSalvo.setDataInscricao(requestValido.getDataInscricao());
        editalSalvo.setDataFinalizacao(requestValido.getDataFinalizacao());
    }

    // TESTES DE POST

    @Test
    void criarEditalComSucesso() throws Exception {
        when(service.salvar(any(EditalExtraSisu.class))).thenReturn(editalSalvo);

        mockMvc.perform(post("/editais")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestValido)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Edital 2025.1"));
    }

    @Test
    void editalFaltandoDadosObrigatorios() throws Exception {
        EditalRequest requestInvalido = new EditalRequest(); 

        mockMvc.perform(post("/editais")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestInvalido)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Erro de validação"));
    }

    // TESTES DE GET

    @Test
    void buscarPorIdExistente() throws Exception {
        when(service.buscarPorId(1L)).thenReturn(editalSalvo);

        mockMvc.perform(get("/editais/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Edital 2025.1"));
    }

    @Test
    void buscarIdInexistente() throws Exception {
        when(service.buscarPorId(99L)).thenThrow(new RuntimeException("Edital não encontrado"));

        mockMvc.perform(get("/editais/{id}", 99L))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Erro de Regra de Negócio"));
    }

    @Test
    void listarTodosPaginado() throws Exception {
        PageImpl<EditalExtraSisu> page = new PageImpl<>(List.of(editalSalvo));
        
        when(service.listarTodos(any(), any())).thenReturn(page);

        mockMvc.perform(get("/editais")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].nome").value("Edital 2025.1")); 
    }

    // TESTES DE PUT

    @Test
    void atualizarComSucesso() throws Exception {
        when(service.atualizar(eq(1L), any(EditalExtraSisu.class))).thenReturn(editalSalvo);

        mockMvc.perform(put("/editais/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestValido)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Edital 2025.1"));
    }

    // TESTES DE DELETE

    @Test
    void deletarComSucesso() throws Exception {
        doNothing().when(service).deletar(1L);

        mockMvc.perform(delete("/editais/{id}", 1L))
                .andExpect(status().isNoContent()); 
    }
}