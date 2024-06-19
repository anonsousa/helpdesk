package br.com.it.helpdesk.domain.dtos;

import br.com.it.helpdesk.domain.models.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record TicketReturnDto(
        UUID id,
        String title,
        String description,
        StatusEnum status,
        PriorityEnum priority,
        DepartmentEnum department,
        LocalDateTime createdDate,
        User userId,
        User assignedToId,
        Set<Comment> commentIds
) {
    public TicketReturnDto(Ticket ticket){
        this(
                ticket.getId(),
                ticket.getTitle(),
                ticket.getDescription(),
                ticket.getStatus(),
                ticket.getPriority(),
                ticket.getDepartment(),
                ticket.getCreatedDate(),
                ticket.getUser(),
                ticket.getAssignedTo(),
                ticket.getComments()
        );
    }
}
