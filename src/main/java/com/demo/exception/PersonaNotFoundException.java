package com.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value= HttpStatus.NOT_FOUND, reason = "La persona no fue encontrada.")
public class PersonaNotFoundException extends RuntimeException{

    public PersonaNotFoundException(String message){
        super(message);
    }

}
