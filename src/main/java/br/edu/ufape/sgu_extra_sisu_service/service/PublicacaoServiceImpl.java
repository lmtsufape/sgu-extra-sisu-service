package br.edu.ufape.sgu_extra_sisu_service.service;

import br.edu.ufape.sgu_extra_sisu_service.model.EditalExtraSisu;
import br.edu.ufape.sgu_extra_sisu_service.model.Publicacao;
import br.edu.ufape.sgu_extra_sisu_service.repository.EditalExtraSisuRepository;
import br.edu.ufape.sgu_extra_sisu_service.repository.PublicacaoRepository;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PublicacaoServiceImpl implements PublicacaoService {

    private final PublicacaoRepository repository;
    private final EditalExtraSisuRepository editalRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<Publicacao> listarTodos(Predicate filtro, Pageable pageable) {
        if (filtro == null) {
            return repository.findAll(pageable);
        }
        return repository.findAll(filtro, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Publicacao buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publicação não encontrada com id: " + id));
    }

    @Override
    @Transactional
    public Publicacao salvar(Publicacao publicacao) {
        vincularEdital(publicacao);
        return repository.save(publicacao);
    }

    @Override
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

    @Override
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
