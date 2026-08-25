package com.example.rest_validacao.Controller;


import com.example.rest_validacao.Models.Tarefa;
import com.example.rest_validacao.Repository.TarefaRepository;
import com.example.rest_validacao.Services.TarefaService;
import com.example.rest_validacao.dtos.TarefaPutRequestDTO;
import com.example.rest_validacao.dtos.TarefaRequestDTO;
import com.example.rest_validacao.dtos.TarefaResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.ServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/tarefas")
@Tag(
  name="Tarefas",
  description = "Operações CRUD relacionadas a tarefas"
)
public class TarefaController {
    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService){
        this.tarefaService = tarefaService;
    }

    //GET
    @Operation(
            summary = "Listagem das tarefas",
            description="Listagem de todas as  tarefas"
    )
    @ApiResponse(
            responseCode = "200",
            description = "A operação de leitura foi realizada com sucesso"
    )
    @GetMapping
    public ResponseEntity<List<TarefaResponseDTO>> getTarefas(){
        return ResponseEntity.ok(tarefaService.listarTarefas()); //RETORNA 200
    }

    //POST
    @Operation(
            summary = "Criar tarefa",
            description="Criação das tarefas"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Uma nova tarefa foi criada com sucesso"
    )
    @ApiResponse(
            responseCode = "400",
            description = "Dados fornecidos são inválidos"
    )
    @PostMapping
    public ResponseEntity<TarefaResponseDTO> criarTarefa(
            @Parameter(
                    description = "ID associado a tarefa específica",
                    example = "1"
    ) @PathVariable Long id,
        @Valid @RequestBody TarefaRequestDTO tarefaRequestDTO){
        TarefaResponseDTO tarefaResponseDTO = tarefaService.criarNovaTarefa(tarefaRequestDTO);
        URI endereco = URI.create("/api/v1/tarefas"  + tarefaResponseDTO.getId());
        return ResponseEntity.created(endereco).body(tarefaResponseDTO); //RETORNA 201
    }

    //PUT
    @Operation(
            summary = "Atualização tarefa",
            description="Atualização das tarefas"
    )
    @PutMapping("/{id}")
    public ResponseEntity<TarefaResponseDTO> atualizarTarefa(
            @PathVariable Long id,
            @Valid @RequestBody TarefaPutRequestDTO dto)
    {
        TarefaResponseDTO tarefaResponseDTO = tarefaService.atualizarTarefa(id, dto);
        if (tarefaResponseDTO != null) {
            return ResponseEntity.ok().body(tarefaResponseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //DELETE
    @Operation(
            summary = "Excluir tarefa",
            description="Exclusão de tarefas"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable Long id, ServletRequest servletRequest){
        boolean deletar = tarefaService.deletarTarefa(id);

        if (deletar){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }



}