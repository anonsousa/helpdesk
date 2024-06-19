package br.com.it.helpdesk.domain.dtos;

import br.com.it.helpdesk.domain.models.Comment;
import br.com.it.helpdesk.domain.models.DepartmentEnum;
import br.com.it.helpdesk.domain.models.PriorityEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record TicketAddDto(
        @NotBlank
        String title,
        @NotBlank
        String description,
        @NotNull
        PriorityEnum priority,
        @NotNull
        DepartmentEnum department,
        @NotNull
        UUID userId,
        @NotNull
        UUID assignedToId,
        @NotNull
        CommentAddDto coment
) {
}
