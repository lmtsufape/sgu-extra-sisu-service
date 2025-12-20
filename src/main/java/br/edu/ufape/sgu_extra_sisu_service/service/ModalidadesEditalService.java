package br.edu.ufape.sgu_extra_sisu_service.service;

import java.util.List;

import br.edu.ufape.sgu_extra_sisu_service.dto.request.ModalidadesEditalRequest;
import br.edu.ufape.sgu_extra_sisu_service.dto.response.ModalidadesEditalResponse;
import br.edu.ufape.sgu_extra_sisu_service.model.Modalidade;
import br.edu.ufape.sgu_extra_sisu_service.model.ModalidadesEdital;
import br.edu.ufape.sgu_extra_sisu_service.repository.ModalidadeRepository;
import br.edu.ufape.sgu_extra_sisu_service.repository.ModalidadesEditalRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModalidadesEditalService {

    private final ModalidadesEditalRepository repository;
    private final ModalidadeRepository modalidadeRepository;

    public ModalidadesEditalResponse criar(ModalidadesEditalRequest request) {

        List<Modalidade> modalidades = modalidadeRepository
                .findAllById(request.getModalidadesIds());

        if (modalidades.size() != request.getModalidadesIds().size()) {
            throw new EntityNotFoundException("Uma ou mais modalidades não foram encontradas");
        }

        ModalidadesEdital entity = new ModalidadesEdital();
        entity.setEditalId(request.getEditalId());
        entity.setModalidades(modalidades);

        return ModalidadesEditalResponse.fromEntity(
                repository.save(entity)
        );
    }

    public ModalidadesEditalResponse buscarPorId(Long id) {
        return repository.findById(id)
                .map(ModalidadesEditalResponse::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("ModalidadesEdital não encontrado"));
    }

    public List<ModalidadesEditalResponse> listar() {
        return repository.findAll()
                .stream()
                .map(ModalidadesEditalResponse::fromEntity)
                .toList();
    }

    public ModalidadesEditalResponse atualizar(Long id, ModalidadesEditalRequest request) {

        ModalidadesEdital entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ModalidadesEdital não encontrado"));

        List<Modalidade> modalidades = modalidadeRepository
                .findAllById(request.getModalidadesIds());

        if (modalidades.size() != request.getModalidadesIds().size()) {
            throw new EntityNotFoundException("Uma ou mais modalidades não foram encontradas");
        }

        entity.setEditalId(request.getEditalId());
        entity.setModalidades(modalidades);

        return ModalidadesEditalResponse.fromEntity(
                repository.save(entity)
        );
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
