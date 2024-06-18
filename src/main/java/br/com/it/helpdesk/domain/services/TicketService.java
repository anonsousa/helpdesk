package br.com.it.helpdesk.domain.services;

import br.com.it.helpdesk.domain.dtos.TicketAddDto;
import br.com.it.helpdesk.domain.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    @Autowired
    private TicketRepository repository;


    public TicketAddDto createTicket(TicketAddDto ticket){

    }


}
