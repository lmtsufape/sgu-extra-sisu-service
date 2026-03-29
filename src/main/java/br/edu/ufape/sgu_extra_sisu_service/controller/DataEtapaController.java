package br.edu.ufape.sgu_extra_sisu_service.controller;

import br.edu.ufape.sgu_extra_sisu_service.client.DataEtapaServiceClient;
import br.edu.ufape.sgu_extra_sisu_service.controller.request.DataEtapaRequest;
import br.edu.ufape.sgu_extra_sisu_service.controller.response.DataEtapaResponse;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/datas-etapas")
@RequiredArgsConstructor
public class DataEtapaController {

    private final DataEtapaServiceClient dataEtapaServiceClient;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DataEtapaResponse criarDataEtapa(@RequestBody @Valid DataEtapaRequest request) {
        return dataEtapaServiceClient.salvar(request);
    }
}