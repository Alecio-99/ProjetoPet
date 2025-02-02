package com.projeto.pet.controller;

import com.projeto.pet.DTO.AgendamentoDTO;
import com.projeto.pet.entity.EntityAgendamento;
import com.projeto.pet.service.ServiceAgendamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/atualizaStatus")
public class ControllerAgendamento {

    @Autowired
    ServiceAgendamento serviceAgendamento;

    @PostMapping("/")
    public EntityAgendamento criarAgendamento(@RequestBody AgendamentoDTO agendamentoDTO){
        return serviceAgendamento.agendar(agendamentoDTO);
    }

    @GetMapping
    public List<EntityAgendamento> listarAgendamentos(){
        return serviceAgendamento.listarTodos();
    }

    @PutMapping("/{id}/status/{statusDog}")
    public EntityAgendamento atualizarStatus(@PathVariable Long id, @PathVariable String statusDog){
        return serviceAgendamento.atualizarStatus(id, statusDog);
    }

}
