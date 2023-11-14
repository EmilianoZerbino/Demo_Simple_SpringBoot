package com.demo.exception;

public class PersonaAlreadyExistException extends  RuntimeException{

    public PersonaAlreadyExistException(String message){
        super(message);
    }

}
