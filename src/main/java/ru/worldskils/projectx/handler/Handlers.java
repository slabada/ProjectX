package ru.worldskils.projectx.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.worldskils.projectx.DTO.ErrorDTO;
import ru.worldskils.projectx.exception.UserException;

import java.time.LocalDateTime;
import java.util.Objects;

@RestControllerAdvice
public class Handlers {

    @ExceptionHandler({
            UserException.ConflictLoginException.class
    })
    public ResponseEntity<ErrorDTO> handleBadRequest(Exception ex) {
        ErrorDTO error = new ErrorDTO(
                ex.getMessage(),
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value()
        );
        return ResponseEntity.status(error.getCode()).body(error);
    }

    @ExceptionHandler({
            UserException.NoLoginException.class
    })
    public ResponseEntity<ErrorDTO> handleUnauthorized(Exception ex) {
        ErrorDTO error = new ErrorDTO(
                ex.getMessage(),
                LocalDateTime.now(),
                HttpStatus.UNAUTHORIZED.value()
        );
        return ResponseEntity.status(error.getCode()).body(error);
    }

    @ExceptionHandler({
            MethodArgumentNotValidException.class
    })
    public ResponseEntity<ErrorDTO> handleConflict(MethodArgumentNotValidException ex) {
        String errorMessage = Objects.requireNonNull(
                ex.getBindingResult().getFieldError()).getDefaultMessage();
        ErrorDTO error = new ErrorDTO(
                errorMessage,
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value()
        );
        return ResponseEntity.status(error.getCode()).body(error);
    }
}
