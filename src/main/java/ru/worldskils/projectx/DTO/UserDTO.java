package ru.worldskils.projectx.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String login;
}
