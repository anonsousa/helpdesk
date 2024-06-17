package br.com.it.helpdesk.domain.services;

import br.com.it.helpdesk.domain.dtos.UserReturnDto;
import br.com.it.helpdesk.domain.dtos.UserSignUpDto;
import br.com.it.helpdesk.domain.models.User;
import br.com.it.helpdesk.domain.repositories.UserRepository;
import br.com.it.helpdesk.infra.Pbkdf2PasswordEncoder;
import br.com.it.helpdesk.infra.exceptions.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private Pbkdf2PasswordEncoder passwordEncoder;


    public UserReturnDto createUser(UserSignUpDto userSignUpDto){
        var user = new User();

        BeanUtils.copyProperties(userSignUpDto, user);
        user.setPassword(passwordEncoder.encode(userSignUpDto.password()));
        user.setCreatedDate(LocalDateTime.now().withNano(0));
        return new UserReturnDto(repository.save(user));
    }

    public UserReturnDto findUserById(UUID id){
        return new UserReturnDto(repository.findById(id).orElseThrow(() -> new NotFoundException("User not found with id" + id)));
    }
}
