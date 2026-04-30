package br.edu.ufape.sgu_extra_sisu_service.features.tipoedital;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufape.sgu_extra_sisu_service.features.tipoedital.dto.TipoEditalRequest;
import br.edu.ufape.sgu_extra_sisu_service.features.tipoedital.dto.TipoEditalResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tipos-editais")
@RequiredArgsConstructor
public class TipoEditalController {

    private final TipoEditalService tipoEditalService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public TipoEditalResponse criar(@Valid @RequestBody TipoEditalRequest request) {
        return this.tipoEditalService.salvar(request);
    }

    @GetMapping("/{id}")
    public TipoEditalResponse buscarPorId(@PathVariable Long id) {
        return this.tipoEditalService.buscarPorId(id);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping
    public Page<TipoEditalResponse> listar(Pageable pageable) {
        return this.tipoEditalService.listar(pageable);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public TipoEditalResponse atualizar(@PathVariable Long id, @Valid @RequestBody TipoEditalRequest request) {
        return this.tipoEditalService.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        this.tipoEditalService.deletar(id);
    }

}
