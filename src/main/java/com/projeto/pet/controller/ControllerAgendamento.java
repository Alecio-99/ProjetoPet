package com.projeto.pet.controller;

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
    public EntityAgendamento criarAgendamento(@RequestBody EntityAgendamento entityAgendamento){
        return serviceAgendamento.agendar(entityAgendamento.getInicio(), entityAgendamento.getFim());
    }

    @GetMapping
    public List<EntityAgendamento> listarAgendamentos(){
        return serviceAgendamento.listarTodos();
    }

    @PutMapping("/{id}/status/{status}")
    public EntityAgendamento atualizarStatus(@PathVariable Long id, @PathVariable String status){
        return serviceAgendamento.atualizarStatus(id, status);
    }

}
