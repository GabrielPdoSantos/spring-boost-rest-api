package com.example.rest_validacao.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class TarefaResponseDTO {
    private String nome;
    private String email;
    private Long id;
    private Instant dataDeCriacao;
    public String getNome() {
        return nome;
    }


}
