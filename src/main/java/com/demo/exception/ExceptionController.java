package com.demo.exception;

import com.demo.dto.response.ErrorDto;
import com.demo.dto.response.ErrorValidationDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(PersonaNotFoundException.class)
    public ResponseEntity<ErrorDto> personaNotFound(PersonaNotFoundException ex){
        return new ResponseEntity<>(new ErrorDto(404,ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PersonaAlreadyExistException.class)
    public ResponseEntity<ErrorDto> personaAlreadyExist(PersonaAlreadyExistException ex){
        return new ResponseEntity<>(new ErrorDto(400,ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InsertionDBException.class)
    public ResponseEntity<ErrorDto> InsertionDB(InsertionDBException ex){
        return new ResponseEntity<>(new ErrorDto(400,ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorValidationDto> fallaValidacion(MethodArgumentNotValidException ex){
        HashMap<String,String> errors = new HashMap<>();
        ex.getFieldErrors().forEach(field -> errors.put(field.getField(),field.getDefaultMessage()));
        return new ResponseEntity<>(new ErrorValidationDto(400,errors), HttpStatus.BAD_REQUEST);
    }

}
