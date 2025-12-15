package br.edu.ufape.sgu_extra_sisu_service.controller.request;

import br.edu.ufape.sgu_extra_sisu_service.model.EditalExtraSisu;
import br.edu.ufape.sgu_extra_sisu_service.model.Publicacao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PublicacaoRequest {

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 200, message = "O nome deve ter entre 3 e 200 caracteres")
    private String nome;

    @NotBlank(message = "O status é obrigatório")
    private String status;

    @NotBlank(message = "O PDF é obrigatório")
    private String pdf;

    private String texto;

    private LocalDateTime dataPublicacao;

    @NotNull(message = "O id do edital é obrigatório")
    private Long editalId;

    public Publicacao toModel() {
        Publicacao p = new Publicacao();
        p.setNome(this.nome);
        p.setStatus(this.status);
        p.setPdf(this.pdf);
        p.setTexto(this.texto);
        p.setDataPublicacao(this.dataPublicacao);

        EditalExtraSisu edital = new EditalExtraSisu();
        edital.setId(this.editalId);
        p.setEdital(edital);

        return p;
    }
}
