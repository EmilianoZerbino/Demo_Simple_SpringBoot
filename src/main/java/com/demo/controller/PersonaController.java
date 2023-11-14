package com.demo.controller;

import com.demo.dto.request.PersonaRequestDto;
import com.demo.dto.request.PersonaRequestWithIdDto;
import com.demo.dto.response.PersonaResponseDto;
import com.demo.exception.PersonaNotFoundException;
import com.demo.service.IPersonaService;
import com.demo.service.PersonaServiceImp;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("personas")
public class PersonaController {

    //--Inyeccion del Servicio (Inyeccion de Dependencia)----
    private IPersonaService service;

    public PersonaController(PersonaServiceImp service){
    this.service = service;
}

    //--------------------------------------------------------

    //--EndPoints---------------------------------------------------------------

    @GetMapping()
    public ResponseEntity<List<PersonaResponseDto>> getPersonas(){
        return new ResponseEntity<>(service.getPersonas(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaResponseDto> getPersonaById(@PathVariable long id){
        return new ResponseEntity<>(service.getPersonaById(id), HttpStatus.OK);
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<PersonaResponseDto> getPersonaByDni(@PathVariable long dni){
        return new ResponseEntity<>(service.getPersonaByDni(dni), HttpStatus.OK);
    }

    @GetMapping("/edad")
    public ResponseEntity<List<PersonaResponseDto>> getPersonaByEdad(@Nullable Integer min, @Nullable Integer max){
        return new ResponseEntity<>(service.getPersonaByAge(min,max), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<PersonaResponseDto> addPersona(@RequestBody @Valid PersonaRequestDto personaRequestDto){
        return new ResponseEntity<>(service.addPersona(personaRequestDto),HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<PersonaResponseDto> editPersona(@RequestBody @Valid PersonaRequestWithIdDto personaRequestWithIdDto){
        return new ResponseEntity<>(service.editPersona(personaRequestWithIdDto),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletePersona(@PathVariable long id){
        return new ResponseEntity<>(service.deletePersona(id),HttpStatus.OK);
    }

    //--------------------------------------------------------------------------

}
