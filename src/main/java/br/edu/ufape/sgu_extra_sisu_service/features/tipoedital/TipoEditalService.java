package br.edu.ufape.sgu_extra_sisu_service.features.tipoedital;

import org.springframework.stereotype.Service;

import br.edu.ufape.sgu_extra_sisu_service.features.tipoedital.dto.TipoEditalRequest;
import br.edu.ufape.sgu_extra_sisu_service.features.tipoedital.dto.TipoEditalResponse;
import br.edu.ufape.sgu_extra_sisu_service.infrastructure.integration.editais.EditaisGateway;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TipoEditalService {
    
    private final EditaisGateway editaisGateway;

    public TipoEditalResponse salvar(TipoEditalRequest request) {
        return this.editaisGateway.criarTipoEdital(request);
    }

    public TipoEditalResponse atualizar(Long id, TipoEditalRequest request) {
        return this.editaisGateway.atualizarTipoEdital(id, request);
    }

    public void deletar(Long id) {
        this.editaisGateway.deletarTipoEdital(id);
    }

    public TipoEditalResponse buscarPorId(Long id) {
        return this.editaisGateway.buscarPorIdTipoEdital(id);
    }

    public Page<TipoEditalResponse> listar(Predicate filtro, Pageable pageable) {
        return editaisGateway.listarTipoEdital(filtro, pageable);
    }

}
