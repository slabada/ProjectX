package ru.worldskils.projectx.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ErrorDTO {
    private String message;
    private LocalDateTime timestamp;
    private int code;
}
