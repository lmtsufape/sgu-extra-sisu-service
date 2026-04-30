package br.edu.ufape.sgu_extra_sisu_service.infrastructure.integration.auth;

import java.util.List;
import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.ufape.sgu_extra_sisu_service.config.FeignClientConfig;
import br.edu.ufape.sgu_extra_sisu_service.features.user.dto.AlunoResponse;
import br.edu.ufape.sgu_extra_sisu_service.features.user.dto.FuncionarioResponse;

@FeignClient(name = "authServiceClient", url = "${authClient.base-url}", configuration = FeignClientConfig.class)
public interface AuthClient {

    @PostMapping("/aluno/batch")
    List<AlunoResponse> buscarAlunos(@RequestBody List<UUID> userIds);

    @GetMapping("/curso/{id}/alunos")
    List<AlunoResponse> listarAlunosPorCurso(@PathVariable("id") Long id);

    @GetMapping("/aluno/current")
    AlunoResponse getAlunoInfo();

    @GetMapping("/aluno/{userId}")
    AlunoResponse buscarAlunoPorId(@PathVariable("userId") UUID userId);

    @PostMapping("/funcionario/batch")
    List<FuncionarioResponse> buscarFuncionarios(@RequestBody List<UUID> userIds);

    @GetMapping("/funcionario/current")
    FuncionarioResponse getFuncionarioInfo();

    @GetMapping("/funcionario/{userId}")
    FuncionarioResponse buscarFuncionarioPorId(@PathVariable("userId") UUID userId);

    @GetMapping("/aluno")
    AlunoResponse buscarAlunoPorCpf(@RequestParam("cpf") String cpf);
}