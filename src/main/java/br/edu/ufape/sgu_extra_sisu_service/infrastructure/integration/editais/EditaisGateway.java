package br.edu.ufape.sgu_extra_sisu_service.infrastructure.integration.editais;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.edu.ufape.sgu_extra_sisu_service.infrastructure.integration.editais.clients.TipoEditalClient;
import br.edu.ufape.sgu_extra_sisu_service.infrastructure.integration.editais.dto.TipoEditalRequest;
import br.edu.ufape.sgu_extra_sisu_service.infrastructure.integration.editais.dto.TipoEditalResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EditaisGateway {
    private final TipoEditalClient tipoEditalClient;

    public TipoEditalResponse criarTipoEdital(TipoEditalRequest request)
    {
        return this.tipoEditalClient.salvar(request);
    }

    public TipoEditalResponse atualizarTipoEdital(Long id, TipoEditalRequest request)
    {
        return this.tipoEditalClient.atualizar(id, request);
    }

    public void deletarTipoEdital(Long id)
    {
        this.tipoEditalClient.deletar(id);
    }

    public Page<TipoEditalResponse> listarTipoEdital(int page, int size, String sort)
    {
        return tipoEditalClient.listar(page, size, sort);
    }

    public TipoEditalResponse buscarPorIdTipoEdital(Long id)
    {
        return this.tipoEditalClient.buscar(id);
    }

}
