package com.demo.service;

import com.demo.dto.request.PersonaRequestDto;
import com.demo.dto.request.PersonaRequestWithIdDto;
import com.demo.dto.response.PersonaResponseDto;
import com.demo.entity.Persona;
import com.demo.exception.InsertionDBException;
import com.demo.exception.PersonaAlreadyExistException;
import com.demo.exception.PersonaNotFoundException;
import com.demo.repository.IPersonaRepository;
import com.demo.repository.PersonaRepositoryImp;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.catalina.mapper.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonaServiceImp implements IPersonaService{

    //--Inyeccion del Repositorio (Inyeccion de Dependencia)-------------------------------------
    private IPersonaRepository repository;
    ObjectMapper mapper = new ObjectMapper();

    public PersonaServiceImp(PersonaRepositoryImp repository){
        this.repository = repository;
        mapper.registerModule(new JavaTimeModule());
    }

    //-------------------------------------------------------------------------------------------
    @Override
    public List<PersonaResponseDto> getPersonas() {
        List<Persona> listaRepo = repository.findAll();
        if(listaRepo.isEmpty()){
            throw new PersonaNotFoundException("Lista Vacía.");
        }
        return listaRepo.stream()
            .map(persona -> mapper.convertValue(persona, PersonaResponseDto.class))
            .toList();
    }

    @Override
    public List<PersonaResponseDto> getPersonaByAge(Integer min, Integer max) {
        List<Persona> listaRepo = repository.findByAge(min,max);
        if(listaRepo.isEmpty()){
            throw new PersonaNotFoundException("No hay personas dentro del rango de edad especificado."); // Ataja filtros vacios.
        }
        return listaRepo.stream()
                .map(persona -> mapper.convertValue(persona, PersonaResponseDto.class))
                .toList();
    }

    @Override
    public PersonaResponseDto getPersonaById(long id) {
        Persona responseRepo = repository.findById(id);
        if(responseRepo==null){
            throw new PersonaNotFoundException("No hay persona registrada con ese ID."); // Ataja ID no encontrados.
        }
        return mapper.convertValue(responseRepo,PersonaResponseDto.class);
    }

    @Override
    public PersonaResponseDto getPersonaByDni(long dni) {
        Persona responseRepo = repository.findByDni(dni);
        if(responseRepo==null){
            throw new PersonaNotFoundException("No hay persona registrada con ese DNI."); // Ataja DNI no encontrados.
        }
        return mapper.convertValue(responseRepo,PersonaResponseDto.class);
    }

    @Override
    public PersonaResponseDto addPersona(PersonaRequestDto personaRequestDto) {
        Persona persona = mapper.convertValue(personaRequestDto, Persona.class);
        if(repository.findByDni(persona.getDni())!=null){
            throw new PersonaAlreadyExistException("Ya se encuentra una persona registrada con ese DNI"); //Ataja errores de DNI duplicados.
        }
        persona = repository.save(persona);
        if(persona==null){
            throw new InsertionDBException("Error al guardar la persona en la Base de Datos"); // Ataja errores de guardado en la DB.
        }
        return mapper.convertValue(persona,PersonaResponseDto.class);
    }

    @Override
    public PersonaResponseDto editPersona(PersonaRequestWithIdDto personaRequestWithIdDto) {
        Persona responseRepo = repository.findById(personaRequestWithIdDto.getId());
        if(responseRepo==null){
            throw new PersonaNotFoundException("No hay persona registrada con ese ID."); // Ataja ID no encontrados.
        }
        return mapper.convertValue(repository.edit(mapper.convertValue(personaRequestWithIdDto, Persona.class)),PersonaResponseDto.class);
    }

    @Override
    public Boolean deletePersona(long id) {
        Persona responseRepo = repository.findById(id);
        if(responseRepo==null){
            throw new PersonaNotFoundException("No hay persona registrada con ese ID."); // Ataja ID no encontrados.
        }
        return repository.Delete(id);
    }

    //----------------------------------------------------------
}
