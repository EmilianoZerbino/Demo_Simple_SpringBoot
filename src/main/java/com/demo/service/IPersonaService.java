package com.demo.service;

import com.demo.dto.request.PersonaRequestDto;
import com.demo.dto.request.PersonaRequestWithIdDto;
import com.demo.dto.response.PersonaResponseDto;

import java.util.List;

public interface IPersonaService {

    List<PersonaResponseDto> getPersonas();
    List<PersonaResponseDto> getPersonaByAge(Integer min, Integer max);
    PersonaResponseDto getPersonaById(long id);
    PersonaResponseDto getPersonaByDni(long dni);
    PersonaResponseDto addPersona(PersonaRequestDto personaRequestDto);
    PersonaResponseDto editPersona(PersonaRequestWithIdDto personaRequestWithIdDto);
    Boolean deletePersona(long id);
}
