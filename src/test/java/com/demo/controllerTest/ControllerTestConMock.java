package com.demo.controllerTest;

import com.demo.controller.PersonaController;
import com.demo.dto.request.PersonaRequestDto;
import com.demo.dto.response.PersonaResponseDto;
import com.demo.service.PersonaServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.demo.Utils.FactoryOfObjects.newPersonaRequestDto;
import static com.demo.Utils.FactoryOfObjects.newPersonaResponseDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ControllerTestConMock {

    @Mock
    PersonaServiceImp service;

    @InjectMocks
    PersonaController controller;

    @Test
    void addPersonaOKTest() {

        //ARRANGE

        PersonaRequestDto argumentSut = newPersonaRequestDto();
        PersonaResponseDto serviceResponse = newPersonaResponseDto();

        ResponseEntity<PersonaResponseDto> expected = new ResponseEntity<>(newPersonaResponseDto(), HttpStatus.OK);

        when(service.addPersona( any())).thenReturn(serviceResponse);

        //ACT

        ResponseEntity<PersonaResponseDto> actual = controller.addPersona(argumentSut);

        //ASSERTS

        assertEquals(expected,actual);

    }
}
