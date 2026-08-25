package com.example.rest_validacao.Services;


import com.example.rest_validacao.Models.Tarefa;
import com.example.rest_validacao.Repository.TarefaRepository;
import com.example.rest_validacao.dtos.TarefaPutRequestDTO;
import com.example.rest_validacao.dtos.TarefaRequestDTO;
import com.example.rest_validacao.dtos.TarefaResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService{

    private final TarefaRepository tarefaRepository;
    private final ModelMapper modelMapper;

    //INJEÇÃO DE DEPENDÊNCIA
    public TarefaService(TarefaRepository tarefaRepository, ModelMapper modelMapper){
        this.tarefaRepository = tarefaRepository;
        this.modelMapper=  modelMapper;
    }

    //CONVERSÃO de REQUESTDTO PARA TAREFA
    public Tarefa converterRequestDTOParaTarefa(TarefaRequestDTO dto){
        Tarefa novaTarefa = new Tarefa();
        novaTarefa.setNome(dto.getNome());
        novaTarefa.setEmail(dto.getEmail());
        novaTarefa.setSenha(dto.getSenha());
        return novaTarefa;
    }

    //CONVERSAO DE TAREFA PARA RESPONSEDTO
    public TarefaResponseDTO converteTarefaParaResponseDTO(Tarefa tarefa){
        TarefaResponseDTO dto = new TarefaResponseDTO();
        dto.setEmail(tarefa.getEmail());
        dto.setNome(tarefa.getNome());
        dto.setId(tarefa.getId());
        dto.setDataDeCriacao(tarefa.getDataDeCriacao());
        return dto;
    }

    //GET
    public List<TarefaResponseDTO> listarTarefas(){
        return this.tarefaRepository
                .findAll()
                .stream()
                .map(tarefa -> converteTarefaParaResponseDTO(tarefa))
                .toList();
    }
    //POST
    public TarefaResponseDTO criarNovaTarefa(TarefaRequestDTO dto){
        Tarefa novaTarefa = converterRequestDTOParaTarefa(dto);
        Tarefa tarefaSalva = tarefaRepository.save(novaTarefa);
        return converteTarefaParaResponseDTO(tarefaSalva);
    }

    //PUT
    public TarefaResponseDTO atualizarTarefa(Long id, TarefaPutRequestDTO dto){
        Optional<Tarefa> tarefa = tarefaRepository.findById(id);
        if (tarefa.isEmpty()){
            return null;
        }
        Tarefa tarefaAtual = tarefa.get();
        modelMapper.map(dto, tarefaAtual);
        Tarefa tarefaAtualizada = tarefaRepository.save(tarefaAtual);

        return modelMapper.map(tarefaAtualizada, TarefaResponseDTO.class);


    }
    //DELETE
    public boolean deletarTarefa(Long id){
        Optional<Tarefa> tarefa = tarefaRepository.findById(id);
        if (tarefa.isEmpty()){
            return false;
        }
        tarefaRepository.deleteById(id);
        return true;
    }
}
