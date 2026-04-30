package br.edu.ufape.sgu_extra_sisu_service.features.inscricao;

import java.time.LocalDateTime;

import br.edu.ufape.sgu_extra_sisu_service.features.inscricao.dto.HistoricoEtapaInscricaoResponse;
import br.edu.ufape.sgu_extra_sisu_service.features.inscricao.dto.InscricaoRequest;
import br.edu.ufape.sgu_extra_sisu_service.features.inscricao.dto.InscricaoResponse;
import br.edu.ufape.sgu_extra_sisu_service.features.inscricao.dto.IsencaoRequest;
import br.edu.ufape.sgu_extra_sisu_service.features.inscricao.dto.StatusInscricaoPatchRequest;
import br.edu.ufape.sgu_extra_sisu_service.infrastructure.integration.editais.clients.EditalClient;

public class InscricaoService {
   private final EditalClient editaisServiceClient;
    
  
    
    public InscricaoResponse realizarInscricao(Long editalId) {
        InscricaoRequest request = InscricaoRequest.builder()
                .editalId(editalId)
                .build();
        
        return editaisServiceClient.salvarInscricao(request);
    }

    
   
    public InscricaoResponse buscarInscricaoPorId(Long id) {
        return editaisServiceClient.buscarPorIdInscricao(id);
    }

    
   
    public List<InscricaoResponse> listarMinhasInscricoes() {
        return editaisServiceClient.listarMinhasInscricoes();
    }

    
   
    public InscricaoResponse atualizarStatusInscricao(Long idInscricao, Long idNovoStatus, String observacao) {
        StatusInscricaoPatchRequest request = StatusInscricaoPatchRequest.builder()
                .statusId(idNovoStatus)
                .observacao(observacao)
                .build();
        
        return editaisServiceClient.atualizarStatusInscricao(idInscricao, request);
    }

    
    
    public void deletarInscricao(Long id) {
        editaisServiceClient.deletarInscricao(id);
    }


    @Transactional
    public Isencao solicitar(Long usuarioId, IsencaoRequest request) {
        EditalExtraSisu edital = editalRepository.findById(request.getEditalId())
                .orElseThrow(() -> new RuntimeException("Edital não encontrado"));

        // isencao só pd ser solicitada antes do prazo de inscricao
        if (LocalDateTime.now().isAfter(edital.getDataInscricao())) {
            throw new RuntimeException("Prazo de isenção encerrado");
        }

        Isencao isencao = new Isencao();
        isencao.setUsuarioId(usuarioId);
        isencao.setEdital(edital);
        isencao.setDocumentosUrl(request.getDocumentoUrl());
        return repository.save(isencao);
    }

 
    @Transactional
    public Isencao avaliar(Long id, AvaliarIsencaoRequest request) {
        Isencao isencao = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Isenção não encontrada com id: " + id));

        StatusIsencao novoStatus = StatusIsencao.valueOf(request.getStatus().toUpperCase());

        isencao.setStatus(novoStatus);
        isencao.setJustificativa(request.getJustificativa());

        return repository.save(isencao);
    }

        private final EditalClient client;

    

    public HistoricoEtapaInscricaoResponse buscarPorId(Long id) {
        return client.buscarHistoricoPorId(id);
    }

    
  
    public PageResponse<HistoricoEtapaInscricaoResponse> listar() {
        return client.listarHistoricos();
    }
}
