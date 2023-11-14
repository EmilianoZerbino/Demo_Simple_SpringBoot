package com.demo.serviceTest;

import com.demo.dto.request.PersonaRequestDto;
import com.demo.dto.response.PersonaResponseDto;
import com.demo.exception.PersonaAlreadyExistException;
import com.demo.service.IPersonaService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static com.demo.Utils.FactoryOfObjects.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) //Esta anotacion se utiliza para que los Test se ejecuten uno a uno en el orden establecido en las anotaciones Order(x), sirve para que no se alteren datos en la DB que hagan fallar otros Test.
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) // Esta anotacion se utiliza para resetear los contextos en que se ejecutan los metodos luego de ser ejecutados, sirve para que no queden alteradas las DB de un metodo a otro y que fallen los Test que se ejecutan posteriormente por no coincidir los datos.
public class ServiceTestSinMock {

    @Autowired
    IPersonaService personaService;

    @Test
    @Order(1) // Establece el orden del @TestMethodOrder.
    @DisplayName("Test del método getPersonas de PersonaService, con resultado positivo.") // Reemplaza el nombre del Test por la descripcion proporcionada en el String.
    void getPersonasOKTest(){
         //ARRANGE

        List<PersonaResponseDto> expected = listOfPersonas();

        //ACT

        List<PersonaResponseDto> actual = personaService.getPersonas();

        //ASSERT

        assertEquals(expected,actual);
    }

    @Test
    @Order(2)
    @DisplayName("Test del método getPersonasById de PersonaService, con resultado positivo.")
    void getPersonasByIdOKTest(){
        //ARRANGE

        PersonaResponseDto expected = personaResponseDtoId1();

        //ACT

        PersonaResponseDto actual = personaService.getPersonaById(1);

        //ASSERT

        assertEquals(expected,actual);
    }

    @Test
    @Order(3)
    @DisplayName("Test del método addPersona de PersonaServiceImp, con resultado positivo.")
    void addPersonaOKTest(){
        //ARRANGE (Parametros u Objetos necesarios para ejecutar el método y el resultado del mismo)

        PersonaRequestDto argumentSut = newPersonaRequestDto();
        PersonaResponseDto expected = newPersonaResponseDto();

        //ACT

        PersonaResponseDto actual = personaService.addPersona(argumentSut);

        //ASSERT

        assertEquals(expected,actual);

    }

    @Test
    @Order(4)
    @DisplayName("Test del método addPersona de PersonaServiceImp, con resultado negativo. (PersonaAlreadyExistEception)")
    void addPersonaFailTest(){

        //ARRANGE (Parametros y Objetos necesarios para ejecutar el método y el resultado del mismo)

        PersonaRequestDto argumentSut = alreadyDniExistPersonaRequestDto();

        //ACT AND ASSERT

        assertThrows(PersonaAlreadyExistException.class,()->personaService.addPersona(argumentSut)); // Segunda inserción de una persona con un mismo DNI.

    }

}
