package com.demo.Utils;

import com.demo.dto.request.PersonaRequestDto;
import com.demo.dto.response.PersonaResponseDto;
import com.demo.entity.Persona;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FactoryOfObjects {

    //---DTOs----------------------------------------------------------------------

    public static List<PersonaResponseDto> listOfPersonas(){

        List<PersonaResponseDto> response = new ArrayList<>();

        response.add(new PersonaResponseDto(1,12345678,"Juan","Perez", LocalDate.of(2000, 12, 1),22,"Argentina","juanPerez@gmail.com"));
        response.add(new PersonaResponseDto(2,23456789,"Luis","Hernandez", LocalDate.of(1985, 12, 1),35,"Paraguaya","luishernandez@gmail.com"));
        response.add(new PersonaResponseDto(3,34567890,"Maria","Suarez", LocalDate.of(1990, 12, 1),23,"Boliviana","mariaSuarez@gmail.com"));
        response.add(new PersonaResponseDto(4,56789012,"Jose","Lopez", LocalDate.of(2015, 12, 1),52,"Argentina","joselopez@gmail.com"));
        response.add(new PersonaResponseDto(5,67890123,"Ernesto","Dominguez", LocalDate.of(1993, 12, 1),12,"Uruguaya","hernestodominguez@gmail.com"));
        response.add(new PersonaResponseDto(6,78901234,"Hernan","Peralta", LocalDate.of(2005, 12, 1),36,"Argentina","hernanperalta@gmail.com"));
        response.add(new PersonaResponseDto(7,89012345,"Julia","Gomez", LocalDate.of(1983, 12, 1),18,"Venezolana","juliagomez@gmail.com"));
        response.add(new PersonaResponseDto(8,90123456,"Ramona","Rosales", LocalDate.of(1999, 12, 1),75,"Argentina","ramonarosales@gmail.com"));
        response.add(new PersonaResponseDto(9,11111111,"Pedro","Ameguino", LocalDate.of(2020, 12, 1),16,"Venezolana","pedroameguino@gmail.com"));
        response.add(new PersonaResponseDto(10,22222222,"Paco","Sarmiento", LocalDate.of(2000, 12, 1),29,"Argentina","pacosarmiento@gmail.com"));

        return response;
    }

    public static PersonaResponseDto personaResponseDtoId1(){
        //---Objeto Persona con hardcodeado en el repositorio con Id:1---
        return new PersonaResponseDto(1,12345678,"Juan","Perez", LocalDate.of(2000, 12, 1),22,"Argentina","juanPerez@gmail.com");
    }

    public static PersonaRequestDto newPersonaRequestDto(){
        return new PersonaRequestDto(90000000,"Juan","Perez", LocalDate.of(2000, 12, 1),22,"Argentina","juanPerez@gmail.com");
    }

    public static PersonaResponseDto newPersonaResponseDto(){
        return new PersonaResponseDto(11,90000000,"Juan","Perez", LocalDate.of(2000, 12, 1),22,"Argentina","juanPerez@gmail.com");
    }

    public static PersonaRequestDto alreadyDniExistPersonaRequestDto(){
        //---Objeto Persona con el mismo DNI que el hardcodeado en el repositorio con Id:1---
        return new PersonaRequestDto(12345678,"Juan","Perez", LocalDate.of(2000, 12, 1),22,"Argentina","juanPerez@gmail.com");
    }

    public static PersonaRequestDto invalidPersonaRequestDto(){
        //---Objeto Persona con errores de validacion---
        return new PersonaRequestDto(123456789,"Juannnnnnnnnnnnnnnnnnnnnn","Perezzzzzzzzzzzzzzzzzzzzz",
                                        LocalDate.of(2050, 12, 1),131,"Argentinaaaaaaaaaaaaaaaaa",
                                        "juanPerez@@gmail.com");
    }

    //-------------------------------------------------------------------------

    //---Entities--------------------------------------------------------------

    public static Persona newPersonaWithId(){
        return new Persona(11,90000000,"Juan","Perez", LocalDate.of(2000, 12, 1),22,"Argentina","juanPerez@gmail.com");

    }


    //-------------------------------------------------------------------------
}
