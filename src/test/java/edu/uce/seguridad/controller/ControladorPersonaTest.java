package edu.uce.seguridad.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.uce.seguridad.service.service.PersonaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
class ControladorPersonaTest {

    @MockBean
    private PersonaService personaService;

    @Autowired
    MockMvc mvc;

    private ObjectMapper objectMapper;

    @Test
    void elimarPersonaTest() {

    }
}