package com.demo;

import com.demo.dto.request.PersonaRequestDto;
import com.demo.dto.response.PersonaResponseDto;
import com.demo.entity.Persona;
import com.demo.exception.PersonaAlreadyExistException;
import com.demo.service.IPersonaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class DemoPersonaCompletoSinDbApplicationTests {

	@Test
	void contextLoads() {
	}

}

