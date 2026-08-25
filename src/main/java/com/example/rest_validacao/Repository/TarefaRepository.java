package com.example.rest_validacao.Repository;

import com.example.rest_validacao.Models.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    Long id(long id);
}
