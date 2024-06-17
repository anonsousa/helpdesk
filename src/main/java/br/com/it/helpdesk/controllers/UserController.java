package br.com.it.helpdesk.controllers;


import br.com.it.helpdesk.domain.dtos.UserReturnDto;
import br.com.it.helpdesk.domain.dtos.UserSignUpDto;
import br.com.it.helpdesk.domain.dtos.UserUpdateDto;
import br.com.it.helpdesk.domain.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PagedResourcesAssembler<UserReturnDto> pagedResourcesAssembler;

    @PostMapping("/user/register")
    public ResponseEntity<UserReturnDto> createUser(@RequestBody @Valid UserSignUpDto user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserReturnDto> findUserById(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUserById(id));
    }

    @GetMapping("/user")
    public ResponseEntity<PagedModel<EntityModel<UserReturnDto>>> findAllUsers(Pageable pageable){
        Page<UserReturnDto> usersPage = userService.findAllUsers(pageable);
        PagedModel<EntityModel<UserReturnDto>> pagedModel = pagedResourcesAssembler.toModel(usersPage);
        return ResponseEntity.status(HttpStatus.OK).body(pagedModel);
    }

    @PutMapping("/user")
    public ResponseEntity<UserReturnDto> updateUser(@RequestBody @Valid UserUpdateDto updateDto){
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(updateDto));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id){
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
