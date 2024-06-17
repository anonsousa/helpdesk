package br.com.it.helpdesk.domain.dtos;

import br.com.it.helpdesk.domain.models.DepartmentEnum;
import br.com.it.helpdesk.domain.models.RoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UserUpdateDto(
        @NotNull
        UUID id,
        @NotBlank
        String name,
        @Email
        @NotBlank
        String email,
        @NotBlank
        String password,
        @NotNull
        RoleEnum role,
        @NotNull
        DepartmentEnum department) { }
