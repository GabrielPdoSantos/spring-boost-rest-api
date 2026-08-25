package com.example.rest_validacao.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TarefaPutRequestDTO {
    private String nome;
    private String email;
}
