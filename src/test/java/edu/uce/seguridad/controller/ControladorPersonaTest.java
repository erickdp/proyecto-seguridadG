package edu.uce.seguridad.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.uce.seguridad.service.service.PersonaService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static edu.uce.seguridad.data.DatosPersona.getPersona001;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest
class ControladorPersonaTest {

    @MockBean
    private PersonaService personaService;

    @Autowired
    MockMvc mvc;

    private ObjectMapper objectMapper;
}