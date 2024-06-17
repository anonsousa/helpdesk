package br.com.it.helpdesk.domain.dtos;

import br.com.it.helpdesk.domain.models.DepartmentEnum;
import br.com.it.helpdesk.domain.models.RoleEnum;
import br.com.it.helpdesk.domain.models.User;

import java.time.LocalDateTime;
import java.util.UUID;


public record UserReturnDto(
        UUID id,
        String name,
        String email,
        String password,
        RoleEnum role,
        DepartmentEnum department,
        LocalDateTime createdDate
) {
    public UserReturnDto(User user) {
        this(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getRole(),
                user.getDepartment(),
                user.getCreatedDate()
        );
    }
}
