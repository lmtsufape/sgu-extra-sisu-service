package br.edu.ufape.sgu_extra_sisu_service.controller;

import br.edu.ufape.sgu_extra_sisu_service.controller.request.DataEtapaRequest;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.DataEtapaResponse;
import br.edu.ufape.sgu_extra_sisu_service.fachada.Fachada;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/datas-etapas")
public class DataEtapaController {

    @Autowired
    private Fachada fachada;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DataEtapaResponse criarDataEtapa(@RequestBody @Valid DataEtapaRequest request) {
        return fachada.salvarDataEtapa(request);
    }
}