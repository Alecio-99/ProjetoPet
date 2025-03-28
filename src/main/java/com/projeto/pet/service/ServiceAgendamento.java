package com.projeto.pet.service;

import com.projeto.pet.DTO.AgendamentoDTO;
import com.projeto.pet.entity.EntityAgendamento;
import com.projeto.pet.entity.EntityCadastroPet;
import com.projeto.pet.enuns.EnumStatusDog;
import com.projeto.pet.repository.RepositoryAgenda;
import com.projeto.pet.repository.RepositoryCadastroPet;
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
    @Autowired
    RepositoryCadastroPet repositoryCadastroPet;

    public EntityAgendamento agendar(AgendamentoDTO agendamentoDTO){
        validarHorario(agendamentoDTO.getInicio(), agendamentoDTO.getFim());
        System.out.println("ID do Dono recebido: " + agendamentoDTO.getDonoId());


        Optional<EntityCadastroPet> donoOpt = repositoryCadastroPet.findById(agendamentoDTO.getDonoId());
        if(!donoOpt.isPresent()){
            System.out.println("Dono não  encontrado");
            throw new RuntimeException("Dono não encontrado.");
        }

        EntityAgendamento agenda = new EntityAgendamento();
        agenda.setNameDog(agendamentoDTO.getNameDog());
        agenda.setRaca(agendamentoDTO.getRaca());
        agenda.setInicio(agendamentoDTO.getInicio());
        agenda.setFim(agendamentoDTO.getFim());
        agenda.setPorteDog(agendamentoDTO.getPorteDog());
        agenda.setBaia(agendamentoDTO.getBaia());
        agenda.setStatusDog(EnumStatusDog.AGENDADO);
        agenda.setDono(donoOpt.get());
        agenda.setNomeDono(donoOpt.get().getName());

        System.out.println("Dono associado: " + agenda.getDono().getId() + " - " + agenda.getDono().getName());

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
        return repositoryAgenda.existsByInicioBeforeAndFimAfter(inicio, fim);
    }

    @Scheduled(fixedRate = 60000)
    public void atualizarStatusAgendamento(){
        LocalDateTime agora = LocalDateTime.now();

        repositoryAgenda.findByStatusDogAndInicioBefore(EnumStatusDog.AGENDADO, agora)
                .forEach(agendamento ->{
                    agendamento.setStatusDog(EnumStatusDog.PENDENTE);
                    repositoryAgenda.save(agendamento);
                });
    }

    public List<EntityAgendamento> listarTodos() {
        return repositoryAgenda.findAll();
    }

    public EntityAgendamento atualizarStatus(Long id, String statusDog){
        Optional<EntityAgendamento> agendamentoOpt = repositoryAgenda.findById(id);

        if(agendamentoOpt.isPresent()){
            EntityAgendamento agendamento = agendamentoOpt.get();
            agendamento.setStatusDog(EnumStatusDog.valor(statusDog));
            return repositoryAgenda.save(agendamento);
        }else {
            throw new RuntimeException("Agendamento nao encontrado.");
        }
    }
}
