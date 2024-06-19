package br.com.it.helpdesk.domain.dtos;

import jakarta.validation.constraints.NotBlank;

public record CommentAddDto(
        @NotBlank
        String content,
        byte[] file
) {
}
