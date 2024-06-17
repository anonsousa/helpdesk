package br.com.it.helpdesk.domain.dtos;

import br.com.it.helpdesk.domain.models.DepartmentEnum;
import br.com.it.helpdesk.domain.models.RoleEnum;
import br.com.it.helpdesk.domain.models.User;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDateTime;
import java.util.UUID;

@Relation(collectionRelation = "Users")
public record UserReturnDto(
        UUID id,
        String name,
        String email,
        String password,
        RoleEnum role,
        DepartmentEnum department,
        boolean userEnabled,
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
                user.isUserEnabled(),
                user.getCreatedDate()
        );
    }
}
