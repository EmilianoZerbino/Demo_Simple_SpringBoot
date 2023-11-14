package com.demo.serviceTest;

import com.demo.dto.request.PersonaRequestDto;
import com.demo.dto.response.PersonaResponseDto;
import com.demo.entity.Persona;
import com.demo.exception.InsertionDBException;
import com.demo.exception.PersonaAlreadyExistException;
import com.demo.repository.PersonaRepositoryImp;
import com.demo.service.PersonaServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.demo.Utils.FactoryOfObjects.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceTestConMock {

    @Mock
    PersonaRepositoryImp repository;

    @InjectMocks
    PersonaServiceImp service;

    @Test
    void addPersonaOKTest(){

        //ARRANGE

        PersonaRequestDto personaRequestDto = newPersonaRequestDto();
        Persona personaEntity = newPersonaWithId();
        PersonaResponseDto expected = newPersonaResponseDto();

        when(repository.save(any())).thenReturn(personaEntity);

        //ACT

        PersonaResponseDto actual = service.addPersona(personaRequestDto);

        //ASSERTS

        assertEquals(expected,actual);

    }

    @Test
    void addPersonaFailByDuplicationTest(){

        //ARRANGE

        PersonaRequestDto personaRequestDto = newPersonaRequestDto();
        Persona personaEntity = newPersonaWithId();

        when(repository.findByDni(anyLong())).thenReturn(personaEntity);

        //ASSERTS

        assertThrows(PersonaAlreadyExistException.class, ()->service.addPersona(personaRequestDto));

    }

    @Test
    void addPersonaFailByErrorInsertDBTest(){

        //ARRANGE

        PersonaRequestDto argumenSut = newPersonaRequestDto();

        when(repository.save(any())).thenReturn(null);

        //ACT AND ASSERTS

        assertThrows(InsertionDBException.class,()->service.addPersona(argumenSut));

    }

}
