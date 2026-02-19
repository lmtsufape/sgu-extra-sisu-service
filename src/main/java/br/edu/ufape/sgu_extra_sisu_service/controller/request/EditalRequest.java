package br.edu.ufape.sgu_extra_sisu_service.controller.request;

import br.edu.ufape.sgu_extra_sisu_service.model.EditalExtraSisu;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class EditalRequest {

    @NotBlank(message = "O nome é obrigatório") 
    @Size(min = 5, max = 100, message = "O nome deve ter entre 5 e 100 caracteres")
    private String titulo;
    
    @NotBlank(message = "O PDF é obrigatório")
    private String pdf;
    
    @NotNull(message = "Data de inscrição obrigatória")
    private LocalDateTime dataInscricao;
    
    @NotNull(message = "Data de finalização obrigatória")
    private LocalDateTime dataFinalizacao;

    @Size(min = 0, max = 100, message = "A descrição deve ter entre 0 e 100 caracteres")
    private String descricao;

    public EditalExtraSisu toModel() {
        EditalExtraSisu edital = new EditalExtraSisu();
        edital.setTitulo(this.titulo);
        edital.setDescricao(this.descricao);
        edital.setPdf(this.pdf);
        edital.setDataInscricao(this.dataInscricao);
        edital.setDataFinalizacao(this.dataFinalizacao);
        return edital;
    }
}