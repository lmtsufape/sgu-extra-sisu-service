package br.edu.ufape.sgu_extra_sisu_service.features.user;

import br.edu.ufape.sgu_extra_sisu_service.features.user.dto.AlunoResponse;
import br.edu.ufape.sgu_extra_sisu_service.features.user.dto.FuncionarioResponse;
import br.edu.ufape.sgu_extra_sisu_service.infrastructure.integration.auth.AuthClient;

public class UserService {
    private final AuthClient authServiceClient;

    
    public AlunoResponse getAlunoInfo() {
        return authServiceClient.getAlunoInfo();
    }

    
    public AlunoResponse buscarAlunoPorId(UUID userId) {
        return authServiceClient.buscarAlunoPorId(userId);
    }
    
    public List<AlunoResponse> buscarAlunos(List<UUID> userIds) {
        return authServiceClient.buscarAlunos(userIds);
    }

    public List<AlunoResponse> buscarAlunosPorCurso(Long idCurso) {
        return authServiceClient.listarAlunosPorCurso(idCurso);
    }


    public FuncionarioResponse getTecnicoInfo() {
        return authServiceClient.getFuncionarioInfo();
    }


    public FuncionarioResponse buscarTecnicoPorId(UUID userId) {
        return authServiceClient.buscarFuncionarioPorId(userId);
    }



    public List<FuncionarioResponse> buscarTecnicos(List<UUID> userIds) {
        return authServiceClient.buscarFuncionarios(userIds);
    }
}
