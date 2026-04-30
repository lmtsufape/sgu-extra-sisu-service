package br.edu.ufape.sgu_extra_sisu_service.features.user.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioResponse {
    private UUID id;
    private String nome;
    private String nomeSocial;
    private String cpf;
    private String email;
    private String telefone;
    private String siape;
}