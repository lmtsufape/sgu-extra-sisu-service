package br.edu.ufape.sgu_extra_sisu_service.features.edital;

import br.edu.ufape.sgu_extra_sisu_service.features.documento.dto.DocumentoRequest;
import br.edu.ufape.sgu_extra_sisu_service.features.documento.dto.DocumentoResponse;
import br.edu.ufape.sgu_extra_sisu_service.features.edital.dto.AdminEditalDetalhadoResponse;
import br.edu.ufape.sgu_extra_sisu_service.features.edital.dto.AdminEditalRequest;
import br.edu.ufape.sgu_extra_sisu_service.features.edital.dto.EditalRequest;
import br.edu.ufape.sgu_extra_sisu_service.features.edital.dto.EtapaAdminRequest;
import br.edu.ufape.sgu_extra_sisu_service.features.inscricao.dto.HistoricoEtapaInscricaoResponse;
import br.edu.ufape.sgu_extra_sisu_service.features.inscricao.dto.InscricaoDetalhadaResponse;
import br.edu.ufape.sgu_extra_sisu_service.features.inscricao.dto.InscricaoResponse;
import jakarta.transaction.Transactional;

public class EditalService {
    private final EditalExtraSisuRepository repository;

    private final EditalExtraSisuServiceClient editalModuloEditaisClient;

   
    @Transactional(readOnly = true) 
    public Page<EditalExtraSisu> listarTodos(Predicate filtro, Pageable pageable) {
        return repository.findAll(filtro, pageable);
    }

   
    @Transactional(readOnly = true)
    public EditalExtraSisu buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Edital não encontrado com id: " + id));
    }

   
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
   
    public AdminEditalDetalhadoResponse buscarEditalComEtapas(Long id) {
        return new AdminEditalDetalhadoResponse(
                editalServiceClient.buscar(id),
                editalServiceClient.listarEtapasPorEdital(id)
        );
    }

   
    public br.edu.ufape.sgu_extra_sisu_service.features.edital.dto.EditalResponse editarEdital(Long id, AdminEditalRequest request) {
        return editalServiceClient.editar(id, request);
    }

   
    public void deletarEdital(Long id) {
        editalServiceClient.deletar(id);
    }

   
    public br.edu.ufape.sgu_extra_sisu_service.features.edital.dto.EtapaResponse editarEtapa(Long id, EtapaAdminRequest request) {
        return etapaServiceClient.editar(id, request);
    }

   
    public void deletarEtapa(Long id) {
        etapaServiceClient.deletar(id);
    }

   
    public InscricaoDetalhadaResponse buscarDetalhesInscricao(Long id) {
        InscricaoResponse inscricao = editaisClient.buscarPorIdInscricao(id);
        List<HistoricoEtapaInscricaoResponse> historico = editaisClient.listarHistoricos().getContent().stream()
                .filter(item -> item.getInscricao() != null && id.equals(item.getInscricao().getId()))
                .sorted(Comparator.comparing(HistoricoEtapaInscricaoResponse::getDataAcao,
                        Comparator.nullsLast(Comparator.naturalOrder())))
                .toList();

        return new InscricaoDetalhadaResponse(inscricao, historico);
    }

    public DocumentoResponse salvarNoModuloEditais(DocumentoRequest request) {
        return documentoClient.salvar(request);
    }

    public DocumentoResponse editarNoModuloEditais(Long id, DocumentoRequest request) {
        return documentoClient.editar(id, request);
    }

    public DocumentoResponse buscarNoModuloEditais(Long id) {
        return documentoClient.buscar(id);
    }

    public void deletarNoModuloEditais(Long id) {
        documentoClient.deletar(id);
    }


    @Transactional(readOnly = true)
    public Page<Publicacao> listarTodos(Predicate filtro, Pageable pageable) {
        if (filtro == null) {
            return repository.findAll(pageable);
        }
        return repository.findAll(filtro, pageable);
    }

   
    @Transactional(readOnly = true)
    public Publicacao buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publicação não encontrada com id: " + id));
    }

   
    @Transactional
    public Publicacao salvar(Publicacao publicacao) {
        vincularEdital(publicacao);
        return repository.save(publicacao);
    }

   
    @Transactional
    public Publicacao atualizar(Long id, Publicacao publicacaoAtualizada) {
        Publicacao existente = buscarPorId(id);

        existente.setNome(publicacaoAtualizada.getNome());
        existente.setStatus(publicacaoAtualizada.getStatus());
        existente.setPdf(publicacaoAtualizada.getPdf());
        existente.setTexto(publicacaoAtualizada.getTexto());
        existente.setDataPublicacao(publicacaoAtualizada.getDataPublicacao());

        if (publicacaoAtualizada.getEdital() != null && publicacaoAtualizada.getEdital().getId() != null) {
            existente.setEdital(publicacaoAtualizada.getEdital());
            vincularEdital(existente);
        }

        return repository.save(existente);
    }

   
    @Transactional
    public void deletar(Long id) {
        Publicacao p = buscarPorId(id);
        repository.delete(p);
    }

    private void vincularEdital(Publicacao p) {
        if (p.getEdital() != null && p.getEdital().getId() != null) {
            Long editalId = p.getEdital().getId();
            EditalExtraSisu edital = editalRepository.findById(editalId)
                    .orElseThrow(() -> new RuntimeException("Edital não encontrado com id: " + editalId));
            p.setEdital(edital);
        } else {
            throw new RuntimeException("Edital associado não informado");
        }
    }
}
