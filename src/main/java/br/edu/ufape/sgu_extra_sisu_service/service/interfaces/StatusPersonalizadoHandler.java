package br.edu.ufape.sgu_extra_sisu_service.service.interfaces;

import br.edu.ufape.sgu_extra_sisu_service.controller.request.StatusPersonalizadoRequest;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.StatusPersonalizadoResponse;
import java.util.List;

public interface StatusPersonalizadoHandler {
    
    List<StatusPersonalizadoResponse> listarStatusPersonalizados();
    StatusPersonalizadoResponse buscarStatusPersonalizadoPorId(Long id);
    StatusPersonalizadoResponse criarStatusPersonalizado(StatusPersonalizadoRequest request);
    StatusPersonalizadoResponse atualizarStatusPersonalizado(Long id, StatusPersonalizadoRequest request);
}
