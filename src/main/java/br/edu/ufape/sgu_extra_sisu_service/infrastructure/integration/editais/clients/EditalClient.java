package br.edu.ufape.sgu_extra_sisu_service.infrastructure.integration.editais.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.edu.ufape.sgu_extra_sisu_service.config.FeignClientConfig;
import br.edu.ufape.sgu_extra_sisu_service.controller.request.StatusPersonalizadoRequest;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.PageResponse;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.StatusPersonalizadoResponse;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.ValorCampoResponse;
import br.edu.ufape.sgu_extra_sisu_service.features.edital.dto.ValorCampoRequest;
import br.edu.ufape.sgu_extra_sisu_service.features.inscricao.dto.HistoricoEtapaInscricaoResponse;
import br.edu.ufape.sgu_extra_sisu_service.features.inscricao.dto.InscricaoRequest;
import br.edu.ufape.sgu_extra_sisu_service.features.inscricao.dto.InscricaoResponse;
import br.edu.ufape.sgu_extra_sisu_service.features.inscricao.dto.StatusInscricaoPatchRequest;

@FeignClient(name = "sgu-editais-service", contextId="editalClient",configuration = FeignClientConfig.class)
public interface EditalClient {

    // ================== Inscrição ==================

    @PostMapping("/inscricao")
    InscricaoResponse salvarInscricao(@RequestBody InscricaoRequest request);

    @GetMapping("/inscricao/{id}")
    InscricaoResponse buscarPorIdInscricao(@PathVariable("id") Long id);

    @GetMapping("/inscricao/atual")
    List<InscricaoResponse> listarMinhasInscricoes();

    @PatchMapping("/inscricao/{id}/status")
    InscricaoResponse atualizarStatusInscricao(
            @PathVariable("id") Long id, 
            @RequestBody StatusInscricaoPatchRequest request
    );

    @DeleteMapping("/inscricao/{id}")
    void deletarInscricao(@PathVariable("id") Long id);

    // ================== Status personalizado ==================

    @GetMapping("/status-personalizado")
    List<StatusPersonalizadoResponse> listarStatusPersonalizados();

    @GetMapping("/status-personalizado/{id}")
    StatusPersonalizadoResponse buscarStatusPersonalizadoPorId(@PathVariable("id") Long id);

    @PostMapping("/status-personalizado")
    StatusPersonalizadoResponse criarStatusPersonalizado(@RequestBody StatusPersonalizadoRequest request);

    @PatchMapping("/status-personalizado/{id}")
    StatusPersonalizadoResponse atualizarStatusPersonalizado(@PathVariable("id") Long id, @RequestBody StatusPersonalizadoRequest request);

    // ================== Valor campo ==================

    @PostMapping("/valor-campo")
    ValorCampoResponse salvarValorCampo(@RequestBody ValorCampoRequest request);

    @PatchMapping("/valor-campo/{id}")
    ValorCampoResponse atualizarValorCampo(@PathVariable("id") Long id, @RequestBody ValorCampoRequest request);

    @GetMapping("/valor-campo/{id}")
    ValorCampoResponse buscarValorCampoPorId(@PathVariable("id") Long id);

    @GetMapping("/valor-campo")
    PageResponse<ValorCampoResponse> listarValoresCampo(); 

    @DeleteMapping("/valor-campo/{id}")
    void deletarValorCampo(@PathVariable("id") Long id);

    // ================== Histórico etapa inscrição ==================

    @GetMapping("/historico-etapa-inscricao/{id}")
    HistoricoEtapaInscricaoResponse buscarHistoricoPorId(@PathVariable("id") Long id);

    @GetMapping("/historico-etapa-inscricao")
    PageResponse<HistoricoEtapaInscricaoResponse> listarHistoricos();


    @GetMapping("/editais/{id}")
    EditalExtraSisuDTO buscarPorId(@PathVariable("id") Long id);

    @GetMapping("/edital-extra-sisu")
    Page<EditalExtraSisu> listarTodos(@RequestParam("page") int page, @RequestParam("size") int size);

    @GetMapping("/editais")
    List<EditalExtraSisuDTO> buscarTodosEditaisExternos();

    @GetMapping("/edital/{id}")
    EditalResponse buscar(@PathVariable("id") Long id);

    @GetMapping("/edital/{id}/etapas")
    List<EtapaResponse> listarEtapasPorEdital(@PathVariable("id") Long id);

    @PatchMapping("/edital/{id}")
    EditalResponse editar(@PathVariable("id") Long id, @RequestBody AdminEditalRequest request);

    @DeleteMapping("/edital/{id}")
    void deletar(@PathVariable("id") Long id);
    
}
