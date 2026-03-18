package br.edu.ufape.sgu_extra_sisu_service.service.interfaces;


import br.edu.ufape.sgu_extra_sisu_service.controller.request.AvaliarIsencaoRequest;
import br.edu.ufape.sgu_extra_sisu_service.controller.request.IsencaoRequest;
import br.edu.ufape.sgu_extra_sisu_service.model.Isencao;

public interface IsencaoInterface {
    public Isencao solicitar(Long usuarioId, IsencaoRequest request);
    public Isencao avaliar(Long usuarioId, AvaliarIsencaoRequest request);
}
