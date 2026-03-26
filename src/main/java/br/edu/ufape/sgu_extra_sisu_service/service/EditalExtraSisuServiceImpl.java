package br.edu.ufape.sgu_extra_sisu_service.service;

import br.edu.ufape.sgu_extra_sisu_service.client.EditalExtraSisuServiceClient;
//import br.edu.ufape.sgu_extra_sisu_service.comunicacao.dto.edital.EditalModuloEditaisRequest;
import br.edu.ufape.sgu_extra_sisu_service.comunicacao.dto.edital.EditalExtraSisuDTO;
import br.edu.ufape.sgu_extra_sisu_service.controller.request.EditalRequest;
import br.edu.ufape.sgu_extra_sisu_service.model.EditalExtraSisu;
import br.edu.ufape.sgu_extra_sisu_service.repository.EditalExtraSisuRepository;
import br.edu.ufape.sgu_extra_sisu_service.service.interfaces.EditalExtraSisuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.querydsl.core.types.Predicate;

@Service
@RequiredArgsConstructor 
public class EditalExtraSisuServiceImpl implements EditalExtraSisuService {

    private final EditalExtraSisuRepository repository;

    private final EditalExtraSisuServiceClient editalModuloEditaisClient;

    @Override
    @Transactional(readOnly = true) 
    public Page<EditalExtraSisu> listarTodos(Predicate filtro, Pageable pageable) {
        return repository.findAll(filtro, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public EditalExtraSisu buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Edital não encontrado com id: " + id));
    }

    @Override
    @Transactional
    public EditalExtraSisu salvar(EditalExtraSisu edital) {
        validarDatas(edital);

        // 1. Mapeia para o formato do módulo de Editais ANTES de salvar local
        // para garantir que os dados de integração estão consistentes
        EditalRequest integrationRequest = new EditalRequest();
        integrationRequest.setTitulo(edital.getTitulo());
        integrationRequest.setDescricao(edital.getDescricao());
        integrationRequest.setDataInscricao(edital.getDataInscricao());
        integrationRequest.setDataFinalizacao(edital.getDataFinalizacao());



        // 2. Tenta salvar no módulo de Editais primeiro
        // O FeignClientConfig enviará o Token JWT automaticamente agora
        //editalModuloEditaisClient.criarNoModuloEditais(integrationRequest);

        // 3. Se a chamada acima não lançar exceção, salva localmente
        return repository.save(edital);
    }

    @Override
    @Transactional
    public EditalExtraSisu atualizar(Long id, EditalExtraSisu editalAtualizado) {
        EditalExtraSisu editalExistente = buscarPorId(id);
        
        editalExistente.setTitulo(editalAtualizado.getTitulo());
        editalExistente.setDescricao(editalAtualizado.getDescricao());
        editalExistente.setPdf(editalAtualizado.getPdf());
        editalExistente.setDataInscricao(editalAtualizado.getDataInscricao());
        editalExistente.setDataFinalizacao(editalAtualizado.getDataFinalizacao());
        
        validarDatas(editalExistente);
        
        return repository.save(editalExistente);
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        EditalExtraSisu edital = buscarPorId(id); 
        repository.delete(edital);
    }

    private void validarDatas(EditalExtraSisu edital) {
        if (edital.getDataInscricao() != null && edital.getDataFinalizacao() != null) {
            if (edital.getDataFinalizacao().isBefore(edital.getDataInscricao())) {
                throw new RuntimeException("A data final não pode ser anterior à data de inscrição."); 
            }
        }
    }
} 
