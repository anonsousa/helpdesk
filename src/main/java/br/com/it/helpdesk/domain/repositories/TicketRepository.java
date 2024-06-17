package br.com.it.helpdesk.domain.repositories;

import br.com.it.helpdesk.domain.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, UUID> {
}
