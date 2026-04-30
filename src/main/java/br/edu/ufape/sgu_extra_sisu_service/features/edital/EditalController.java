package br.edu.ufape.sgu_extra_sisu_service.features.edital;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;

import br.edu.ufape.sgu_extra_sisu_service.auth.AuthenticatedUserProvider;
import br.edu.ufape.sgu_extra_sisu_service.features.edital.dto.AdminEditalDetalhadoResponse;
import br.edu.ufape.sgu_extra_sisu_service.features.edital.dto.AdminEditalRequest;
import br.edu.ufape.sgu_extra_sisu_service.features.edital.dto.DataEtapaRequest;
import br.edu.ufape.sgu_extra_sisu_service.features.edital.dto.DataEtapaResponse;
import br.edu.ufape.sgu_extra_sisu_service.features.edital.dto.EditalRequest;
import br.edu.ufape.sgu_extra_sisu_service.features.edital.dto.EditalResponse;
import br.edu.ufape.sgu_extra_sisu_service.features.edital.dto.EtapaAdminRequest;
import br.edu.ufape.sgu_extra_sisu_service.features.edital.dto.EtapaRequest;
import br.edu.ufape.sgu_extra_sisu_service.features.edital.dto.EtapaResponse;
import br.edu.ufape.sgu_extra_sisu_service.features.edital.dto.PublicacaoRequest;
import br.edu.ufape.sgu_extra_sisu_service.features.edital.dto.PublicacaoResponse;
import br.edu.ufape.sgu_extra_sisu_service.features.edital.dto.ValorCampoRequest;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/editais")
// @Tag(name = "Valor Campo", description = "Gerencia as respostas dos campos personalizados")
@RequiredArgsConstructor
public class EditalController {

    private final EditalService editalService;
    private final AuthenticatedUserProvider userauth;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public EditalResponse criarEdital(@RequestBody @Valid EditalRequest novo) {
        EditalExtraSisu edital = novo.toModel();
        edital = extraSisuService.salvar(edital);

        return new EditalResponse(edital);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EditalResponse> localizarPorId(@PathVariable("id") Long id) {
        EditalExtraSisu edital = extraSisuService.buscarPorId(id);

        return ResponseEntity.ok(new EditalResponse(edital));
    }

    @GetMapping
    public UUID listarTodos(
            @QuerydslPredicate(root = EditalExtraSisu.class) Predicate filtro,
            Pageable pageable) {
                UUID userId = userauth.getUserId();
                

        return userId;
    }

    @PutMapping("/{id}")
    public EditalResponse atualizar(@PathVariable Long id, @RequestBody @Valid EditalRequest requisicao) {
        EditalExtraSisu editalAtualizado = requisicao.toModel();
        EditalExtraSisu salvo = extraSisuService.atualizar(id, editalAtualizado);
        
        return new EditalResponse(salvo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        extraSisuService.deletar(id);
    }

        @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DataEtapaResponse criarDataEtapa(@RequestBody @Valid DataEtapaRequest request) {
        return dataEtapaServiceClient.salvar(request);
    }

        private final EtapaServiceHandler etapaHandler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EtapaResponse criarEtapa(@RequestBody @Valid EtapaRequest request) {
        return etapaHandler.salvarNoModuloEditais(request);
    }

    @GetMapping("/{id}")
    public EtapaResponse buscarPorId(@PathVariable Long id) {
        EtapaResponse response = etapaHandler.buscarNoModuloEditais(id);
        if (response == null) {
            throw new RuntimeException("Etapa não encontrada no módulo de Editais");
        }
        return response;
    }

     private final PublicacaoService service;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public PublicacaoResponse criar(@RequestBody @Valid PublicacaoRequest novo) {
        Publicacao p = novo.toModel();
        p = service.salvar(p);
        return new PublicacaoResponse(p);
    }

    @GetMapping("/{id}")
    public PublicacaoResponse localizarPorId(@PathVariable("id") Long id) {
        Publicacao p = service.buscarPorId(id);
        return new PublicacaoResponse(p);
    }

    @GetMapping
    public Page<PublicacaoResponse> listarTodos(@QuerydslPredicate(root = Publicacao.class) Predicate filtro,
            Pageable pageable) {
        return service.listarTodos(filtro, pageable).map(PublicacaoResponse::new);
    }

    @PutMapping("/{id}")
    public PublicacaoResponse atualizar(@PathVariable Long id, @RequestBody @Valid PublicacaoRequest requisicao) {
        Publicacao atualizado = requisicao.toModel();
        Publicacao salvo = service.atualizar(id, atualizado);
        return new PublicacaoResponse(salvo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }



    private final ValorCampoHandler valorCampoHandler;

    @PostMapping
    @Operation(summary = "Salvar resposta de campo")
    public ResponseEntity<ValorCampoResponse> salvar(@RequestBody ValorCampoRequest request) {
        return new ResponseEntity<>(valorCampoHandler.salvar(request), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Atualizar resposta")
    public ResponseEntity<ValorCampoResponse> editar(@PathVariable Long id, @RequestBody ValorCampoRequest request) {
        return ResponseEntity.ok(valorCampoHandler.atualizar(id, request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar resposta por ID")
    public ResponseEntity<ValorCampoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(valorCampoHandler.buscarPorId(id));
    }

    @GetMapping
    @Operation(summary = "Listar todas as respostas")
    public ResponseEntity<PageResponse<ValorCampoResponse>> listar() {
        return ResponseEntity.ok(valorCampoHandler.listar());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar resposta")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        valorCampoHandler.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // logica para admin

      @GetMapping("/editais/{id}")
    public ResponseEntity<AdminEditalDetalhadoResponse> buscarEditalComEtapas(@PathVariable Long id) {
        return ResponseEntity.ok(editalAdminHandler.buscarEditalComEtapas(id));
    }

    @PatchMapping("/editais/{id}")
    public ResponseEntity<EditalResponse> editarEdital(@PathVariable Long id, @RequestBody AdminEditalRequest request) {
        return ResponseEntity.ok(editalAdminHandler.editarEdital(id, request));
    }

    @DeleteMapping("/editais/{id}")
    public ResponseEntity<Void> deletarEdital(@PathVariable Long id) {
        editalAdminHandler.deletarEdital(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/etapas/{id}")
    public ResponseEntity<EtapaResponse> editarEtapa(@PathVariable Long id, @RequestBody EtapaAdminRequest request) {
        return ResponseEntity.ok(editalAdminHandler.editarEtapa(id, request));
    }

    @DeleteMapping("/etapas/{id}")
    public ResponseEntity<Void> deletarEtapa(@PathVariable Long id) {
        editalAdminHandler.deletarEtapa(id);
        return ResponseEntity.noContent().build();
    }
}