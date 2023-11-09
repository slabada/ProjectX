package ru.worldskils.projectx.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.worldskils.projectx.DTO.LoginDTO;
import ru.worldskils.projectx.DTO.UserDTO;
import ru.worldskils.projectx.models.UserModel;
import ru.worldskils.projectx.service.UserService;

@RestController
public class UserController {

    protected final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO registration(@RequestBody @Valid UserModel u){
        return userService.registration(u);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public String login(@RequestBody @Valid LoginDTO l){
        return userService.login(l);
    }

}
