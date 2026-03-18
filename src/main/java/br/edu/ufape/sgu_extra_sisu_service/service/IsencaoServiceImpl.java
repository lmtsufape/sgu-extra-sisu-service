package br.edu.ufape.sgu_extra_sisu_service.service;

import br.edu.ufape.sgu_extra_sisu_service.controller.request.AvaliarIsencaoRequest;
import br.edu.ufape.sgu_extra_sisu_service.controller.request.IsencaoRequest;
import br.edu.ufape.sgu_extra_sisu_service.model.EditalExtraSisu;
import br.edu.ufape.sgu_extra_sisu_service.model.Isencao;
import br.edu.ufape.sgu_extra_sisu_service.model.enums.StatusIsencao;
import br.edu.ufape.sgu_extra_sisu_service.repository.EditalExtraSisuRepository;
import br.edu.ufape.sgu_extra_sisu_service.repository.IsencaoRepository;
import br.edu.ufape.sgu_extra_sisu_service.service.interfaces.IsencaoInterface;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class IsencaoServiceImpl implements IsencaoInterface {
    private final IsencaoRepository repository;
    private final EditalExtraSisuRepository editalRepository;

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

    @Override
    @Transactional
    public Isencao avaliar(Long id, AvaliarIsencaoRequest request) {
        Isencao isencao = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Isenção não encontrada com id: " + id));

        StatusIsencao novoStatus = StatusIsencao.valueOf(request.getStatus().toUpperCase());

        isencao.setStatus(novoStatus);
        isencao.setJustificativa(request.getJustificativa());

        return repository.save(isencao);
    }
}
