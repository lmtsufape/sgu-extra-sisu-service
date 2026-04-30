package br.edu.ufape.sgu_extra_sisu_service.features.inscricao;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufape.sgu_extra_sisu_service.features.inscricao.dto.HistoricoEtapaInscricaoResponse;
import br.edu.ufape.sgu_extra_sisu_service.features.inscricao.dto.InscricaoDetalhadaResponse;
import br.edu.ufape.sgu_extra_sisu_service.features.inscricao.dto.InscricaoResponse;
import br.edu.ufape.sgu_extra_sisu_service.features.inscricao.dto.IsencaoRequest;
import br.edu.ufape.sgu_extra_sisu_service.features.inscricao.dto.IsencaoResponse;
import br.edu.ufape.sgu_extra_sisu_service.features.inscricao.dto.StatusInscricaoPatchRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/inscricoes") 
@Tag(name = "Inscrições Externas", description = "Integração com Sistema de Editais")
@RequiredArgsConstructor
public class InscricaoController {
    private final EditalInscricaoHandler editalInscricaoHandler;
    private final EditalAdminHandler editalAdminHandler;



    @PostMapping("/edital/{editalId}")
    public ResponseEntity<InscricaoResponse> realizarInscricao(@PathVariable Long editalId) {
        InscricaoResponse response = editalInscricaoHandler.realizarInscricao(editalId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<InscricaoResponse>> listarMinhasInscricoes() {
        List<InscricaoResponse> lista = editalInscricaoHandler.listarMinhasInscricoes();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InscricaoResponse> buscarPorId(@PathVariable Long id) {
        InscricaoResponse response = editalInscricaoHandler.buscarInscricaoPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/detalhes")
    public ResponseEntity<InscricaoDetalhadaResponse> buscarDetalhesPorId(@PathVariable Long id) {
        InscricaoDetalhadaResponse response = editalAdminHandler.buscarDetalhesInscricao(id);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<InscricaoResponse> atualizarStatus(
            @PathVariable Long id,
            @RequestBody StatusInscricaoPatchRequest request) { 
        
        InscricaoResponse response = editalInscricaoHandler.atualizarStatusInscricao(
                id, 
                request.getStatusId(), 
                request.getObservacao()
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        editalInscricaoHandler.deletarInscricao(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping
    public IsencaoResponse solicitar(@RequestBody IsencaoRequest req) {
        Long idDoUsuarioLogado = req.getUsuarioId();
        return new IsencaoResponse(isencaoService.solicitar(1L, req));
    }

    @PatchMapping("/{id}/avaliar")
    public IsencaoResponse avaliar(@PathVariable Long id, @RequestBody AvaliarIsencaoRequest req) {
        return new IsencaoResponse(isencaoService.avaliar(id, req));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar registro de histórico por ID")
    public ResponseEntity<HistoricoEtapaInscricaoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(editalHistoricoEtapaHandler.buscarPorId(id));
    }

    @GetMapping
    @Operation(summary = "Listar todos os históricos (Paginado)")
    public ResponseEntity<PageResponse<HistoricoEtapaInscricaoResponse>> listar() {
        return ResponseEntity.ok(editalHistoricoEtapaHandler.listar());
    }
}
