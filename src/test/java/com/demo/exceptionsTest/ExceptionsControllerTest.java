package com.demo.exceptionsTest;

import com.demo.dto.response.ErrorDto;
import com.demo.exception.ExceptionController;
import com.demo.exception.PersonaNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ExceptionsControllerTest {

    @Autowired
    ExceptionController exceptionController;

    @Test
    @DisplayName("Test de PersonaNotFoundException con salida positiva.")
    void personaNotFoundTest(){

        //ARRANGE

        ErrorDto body = new ErrorDto(404,"message");
        PersonaNotFoundException argumentSut = new PersonaNotFoundException("message");
        ResponseEntity<?> expected = new ResponseEntity<>(body, HttpStatus.NOT_FOUND);

        //ACT

        ResponseEntity<?> actual = exceptionController.personaNotFound(argumentSut);

        //ASSERT

        assertEquals(expected,actual);

    }
}
