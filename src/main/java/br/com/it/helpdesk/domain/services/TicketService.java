package br.com.it.helpdesk.domain.services;

import br.com.it.helpdesk.domain.dtos.TicketAddDto;
import br.com.it.helpdesk.domain.dtos.TicketReturnDto;
import br.com.it.helpdesk.domain.models.RoleEnum;
import br.com.it.helpdesk.domain.models.StatusEnum;
import br.com.it.helpdesk.domain.models.Ticket;
import br.com.it.helpdesk.domain.repositories.TicketRepository;
import br.com.it.helpdesk.domain.repositories.UserRepository;
import br.com.it.helpdesk.infra.exceptions.InvalidRequestException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class TicketService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketRepository repository;

    @Autowired
    private ScheduleValidationService scheduleValidationService;



    @Transactional
    public TicketReturnDto createTicket(TicketAddDto ticket){
        var validation = scheduleValidationService.validadeTicketSchedule(LocalDateTime.now());
        var userAssigned = userRepository.findById(ticket.assignedToId());
        var userModel = userRepository.findById(ticket.userId());

        if (validation || userModel.isPresent() ||userAssigned.get().getRole() == RoleEnum.SUPPORT){
            var ticketModel = new Ticket();
            BeanUtils.copyProperties(ticket, ticketModel);
            ticketModel.setStatus(StatusEnum.OPEN);
            ticketModel.setCreatedDate(LocalDateTime.now().withNano(0));
            return new TicketReturnDto(repository.save(ticketModel));
        } else {
            throw new InvalidRequestException("Something went wrong: invalid commercial hour, invalid user or support user");
        }
    }


}
