package com.demo.integration;

import com.demo.dto.request.PersonaRequestDto;
import com.demo.dto.response.ErrorDto;
import com.demo.dto.response.ErrorValidationDto;
import com.demo.dto.response.PersonaResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static com.demo.Utils.FactoryOfObjects.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class IntegrationTest {

    @Autowired
    MockMvc mockMvc;


    @Test
    @DisplayName("Test de integración del método getPersonas del controlador con salido OK")
    void getPersonasOkTest() throws Exception {


        List<PersonaResponseDto> listaDto = listOfPersonas();

        ObjectWriter writer = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .configure(SerializationFeature.WRAP_ROOT_VALUE,false)
                .writer();

        String expected = writer.writeValueAsString(listaDto);

        MvcResult response = mockMvc.perform(get("/personas"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        assertEquals("application/json",response.getResponse().getContentType());
        assertEquals(expected,response.getResponse().getContentAsString());  //PREGUNTAR

    }

    @Test
    @DisplayName("Test de integración del método getPersonasByDni del controlador con salida OK")
    void getPersonasByDniOkTest() throws Exception {

        MvcResult response = mockMvc.perform(get("/personas/dni/{dni}",12345678))
                                     .andDo(print())
                                     .andExpect(content().contentType("application/json"))
                                     .andExpect(status().isOk())
                                     .andReturn();

    }

    @Test
    @DisplayName("Test de integración del método getPersonasByDni del controlador con salida de Exception")
    void getPersonasByDniNotFoundTest() throws Exception {

        mockMvc.perform(get("/personas/dni/{dni}",99999990))// Se debe colocar un mail que no se este en repositorio
                .andDo(print())
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.statusCode").value(404))
                .andExpect(jsonPath("$.message").value("No hay persona registrada con ese DNI."));

    }

    @Test
    @DisplayName("Test de integración del método getPersonasByEdad del controlador con salida OK")
    void getPersonasByEdadOkTest() throws Exception {

        mockMvc.perform(get("/personas/edad").param("min","35").param("max","80"))
                .andDo(print())
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Luis"));

    }

    @Test
    @DisplayName("Test de integración del método addPersona del controlador con salida OK")
    void addPersonasOkTest() throws Exception {

        PersonaRequestDto personaRequestDto = newPersonaRequestDto();
        PersonaResponseDto personaResponseDto = newPersonaResponseDto();

        ObjectWriter writer = new ObjectMapper()
                        .registerModule(new JavaTimeModule())
                        .configure(SerializationFeature.WRAP_ROOT_VALUE,false)
                        .writer();

        String payload = writer.writeValueAsString(personaRequestDto);
        String expected = writer.writeValueAsString(personaResponseDto);

        MvcResult response = mockMvc.perform(post("/personas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertEquals(expected,response.getResponse().getContentAsString());  //PREGUNTAR

    }

    @Test
    @DisplayName("Test de integración del método addPersona del controlador con salida de Excepcion por duplicado")
    void addPersonasFailAlreadyExistTest() throws Exception {

        PersonaRequestDto personaRequestDto = alreadyDniExistPersonaRequestDto();

        ObjectWriter writer = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .configure(SerializationFeature.WRAP_ROOT_VALUE,false)
                .writer();

        String payload = writer.writeValueAsString(personaRequestDto);

        MvcResult response = mockMvc.perform(post("/personas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        ErrorDto errorDto = new ErrorDto(400,"Ya se encuentra una persona registrada con ese DNI");
        String expected = writer.writeValueAsString(errorDto);

        assertEquals(expected,response.getResponse().getContentAsString());

    }

    @Test
    @DisplayName("Test de integración de errores de validación")
    void addPersonasFailValidationTest() throws Exception {

        PersonaRequestDto personaRequestDto = invalidPersonaRequestDto();

        ObjectWriter writer = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .configure(SerializationFeature.WRAP_ROOT_VALUE,false)
                .writer();

        String payload = writer.writeValueAsString(personaRequestDto);

        mockMvc.perform(post("/personas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errors.dni").value("Debe ingresar un valor menor a $99999999"))
                .andExpect(jsonPath("$.errors.nombre").value("El nombre debe contener entre 3 y 24 caracteres."))
                .andExpect(jsonPath("$.errors.apellido").value("El apellido debe contener entre 3 y 24 caracteres."))
                .andExpect(jsonPath("$.errors.fechNac").value("La fecha de nacimiento debe ser igual o anterior al dia de hoy."))
                .andExpect(jsonPath("$.errors.edad").value("El programa registra edades de hasta 130 años"))
                .andExpect(jsonPath("$.errors.nacionalidad").value("La nacionalidad debe contener entre 3 y 24 caracteres."))
                .andExpect(jsonPath("$.errors.email").value("El e-mail ingresado no es valido."));


    }
}
