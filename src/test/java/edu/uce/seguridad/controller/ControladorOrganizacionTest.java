package edu.uce.seguridad.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.uce.seguridad.model.Organizacion;
import edu.uce.seguridad.service.service.OrganizacionService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ControladorOrganizacion.class)
class ControladorOrganizacionTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private OrganizacionService organizacionService;

    private ObjectMapper objectMapper;

    private TestInfo testInfo;
    private TestReporter testReporter;

    @BeforeEach
    void setUp(TestInfo testInfo, TestReporter testReporter) {
        this.testInfo = testInfo;
        this.testReporter = testReporter;

        this.testReporter.publishEntry(new StringBuilder().append("Ejecutando: ").append(this.testInfo.getDisplayName()).append(" - del test: ")
                .append(this.testInfo.getTestMethod().orElse(null).getName()).toString());
        this.objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Endpoint para agregar organización")
    void agregarOrganizacion() throws Exception {
        // Given
        Organizacion organizacion = new Organizacion();
        organizacion.setOrganizacion("UNIVERSIDAD CENTRAL DEL ECUADOR");
        organizacion.setDepartamentos(Arrays.asList("TI", "RECURSOS HUMANOS", "INVESTIGACIÓN"));
        organizacion.setContacto("uce@mail.com");
        when(this.organizacionService.agregar(any())).then(invocation -> {
            Organizacion o = invocation.getArgument(0);
            o.set_id("1");
            return o;
        });

        // When
        this.mvc.perform(post("/sgcnegocio/organizacion/agregarOrganizacion").contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(organizacion)))
                // Then
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$._id").value("1"))
                .andExpect(jsonPath("$.organizacion").value("UNIVERSIDAD CENTRAL DEL ECUADOR"))
                .andExpect(jsonPath("$.departamentos[0]").value("TI"))
                .andExpect(jsonPath("$.departamentos[1]").value("RECURSOS HUMANOS"))
                .andExpect(jsonPath("$.departamentos[2]").value("INVESTIGACIÓN"))
                .andExpect(jsonPath("$.departamentos", hasSize(3)))
                .andExpect(jsonPath("$.contacto").value("uce@mail.com"));

        verify(this.organizacionService).agregar(any(Organizacion.class));
    }

}