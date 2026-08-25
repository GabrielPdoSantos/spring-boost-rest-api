package com.example.rest_validacao.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name="tb_tarefas")
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Nome Inválido")
    @Size(min = 4, max = 99, message = "Nome deve ter mais que 3 caracteres e menos que 99")
    private String nome;
    @Size(min = 4, max = 30, message = "Email deve ter mais que 3 caracteres e menos que 30")
    private String email;

    private String senha;
    @CreationTimestamp
    private Instant dataDeCriacao;

    public Tarefa(String nome) {
        this.nome = nome;
    }

    public Tarefa() {}

    public Instant getDataDeCriacao() {
        return dataDeCriacao;
    }

    public void setDataDeCriacao(Instant dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getId() {
        return id;
    }
}
