package br.com.it.helpdesk.domain.services;

import br.com.it.helpdesk.domain.dtos.UserReturnDto;
import br.com.it.helpdesk.domain.dtos.UserSignUpDto;
import br.com.it.helpdesk.domain.dtos.UserUpdateDto;
import br.com.it.helpdesk.domain.models.User;
import br.com.it.helpdesk.domain.repositories.UserRepository;
import br.com.it.helpdesk.infra.Pbkdf2PasswordEncoder;
import br.com.it.helpdesk.infra.exceptions.InvalidRequestException;
import br.com.it.helpdesk.infra.exceptions.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private Pbkdf2PasswordEncoder passwordEncoder;


    @Transactional
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

    public Page<UserReturnDto> findAllUsers(Pageable pageable){
        Page<User> userModel = repository.findAll(pageable);
        return userModel.map(UserReturnDto::new);
    }

    @Transactional
    public UserReturnDto updateUser(UserUpdateDto userUpdateDto){
        var userModel = repository.findById(userUpdateDto.id());

        if (userModel.isPresent()){

            boolean verifyPass = passwordEncoder.matches(userModel.get().getPassword(), userUpdateDto.password());
            if (verifyPass){
                var user = new User();
                BeanUtils.copyProperties(userUpdateDto, user);
                user.setCreatedDate(LocalDateTime.now().withNano(0));
                return new UserReturnDto(user);
            } else {
                throw new InvalidRequestException("Invalid Password!");
            }

        }

        throw new NotFoundException("User not found!");
    }

    @Transactional
    //Logical delection
    public void deleteUser(UUID id){
        var user = repository.findById(id);
        if (user.isPresent()){
            repository.updateUserEnabledStatus(id, false);
        } else {
            throw new NotFoundException("User not found!");
        }
    }
























}
