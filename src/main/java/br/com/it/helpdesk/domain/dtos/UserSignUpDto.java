package br.com.it.helpdesk.domain.dtos;

import br.com.it.helpdesk.domain.models.DepartmentEnum;
import br.com.it.helpdesk.domain.models.RoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserSignUpDto(
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,
        @NotBlank
                @Size(min = 6, max = 50)
        String password,
        @NotNull
        DepartmentEnum department,
        @NotNull
        RoleEnum role
) {
}
