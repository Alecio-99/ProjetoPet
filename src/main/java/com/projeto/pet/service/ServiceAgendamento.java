package com.projeto.pet.service;

import com.projeto.pet.DTO.AgendamentoDTO;
import com.projeto.pet.entity.EntityAgendamento;
import com.projeto.pet.enuns.EnumStatusDog;
import com.projeto.pet.repository.RepositoryAgenda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceAgendamento {

    @Autowired
    RepositoryAgenda repositoryAgenda;

    public EntityAgendamento agendar(LocalDateTime inicio, LocalDateTime fim){
        validarHorario(inicio, fim);

        EntityAgendamento agenda = new EntityAgendamento();
        agenda.setInicio(inicio);
        agenda.setFim(fim);
        agenda.setStatusDog(EnumStatusDog.AGENDADO);

        return repositoryAgenda.save(agenda);
    }

    private void validarHorario(LocalDateTime inicio, LocalDateTime fim){
        if(inicio.isBefore(LocalDateTime.now())){
            throw new RuntimeException("Não é possível agendar horarios anteriores ao dia de hoje.");
        }
        if(conflitoHorario(inicio, fim)){
            throw new RuntimeException("Ja existe um agendamento para esse horário.");
        }
    }

    private boolean conflitoHorario(LocalDateTime inicio, LocalDateTime fim){
        return repositoryAgenda.existsByInicioLessThanAndFimGreaterThan(inicio, fim);
    }

    @Scheduled(fixedRate = 60000)
    public void atualizarStatusAgendamento(){
        LocalDateTime agora = LocalDateTime.now();

        repositoryAgenda.findByStatus(EnumStatusDog.AGENDADO, agora)
                .forEach(agendamento ->{
                    agendamento.setStatusDog(EnumStatusDog.PENDENTE);
                    repositoryAgenda.save(agendamento);
                });
    }

    public List<EntityAgendamento> listarTodos() {
        return repositoryAgenda.findAll();
    }

    public EntityAgendamento atualizarStatus(Long id, String status){
        Optional<EntityAgendamento> agendamentoOpt = repositoryAgenda.findById(id);

        if(agendamentoOpt.isPresent()){
            EntityAgendamento agendamento = agendamentoOpt.get();
            agendamento.setStatusDog(EnumStatusDog.valor(status));
            return repositoryAgenda.save(agendamento);
        }else {
            throw new RuntimeException("Agendamento nao encontrado.");
        }
    }
}
