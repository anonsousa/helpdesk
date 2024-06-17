package br.com.it.helpdesk.controllers;


import br.com.it.helpdesk.domain.dtos.UserReturnDto;
import br.com.it.helpdesk.domain.dtos.UserSignUpDto;
import br.com.it.helpdesk.domain.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserReturnDto> createUser(@RequestBody @Valid UserSignUpDto user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
    }
}
