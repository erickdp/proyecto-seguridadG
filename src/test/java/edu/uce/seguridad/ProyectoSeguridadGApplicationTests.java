package edu.uce.seguridad;

import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.repository.FormularioLiderazgoRepository;
import edu.uce.seguridad.repository.PersonaRepository;
import edu.uce.seguridad.service.Imp.FormularioLiderazgoServiceImp;
import edu.uce.seguridad.service.Imp.PersonaServiceImp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static edu.uce.seguridad.data.DatosFormularioLiderazgo.getFormularioLiderazgo001;
import static edu.uce.seguridad.data.DatosPersona.getPersona001;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProyectoSeguridadGApplicationTests {

    @Nested
    @DisplayName("Pruebas de los servicios en Persona")
    class personaTest {

        @Mock
        private PersonaRepository personaRepository; // Solo los mocks pueden ser verificados

        @InjectMocks
        private PersonaServiceImp personaService; // esta es una instancia sin simular

        @Test
        void eliminarPersonaTest() {
            when(personaRepository.findPersonaByUsuario("erickdp")).thenReturn(getPersona001().orElse(null));

            NoEncontradoExcepcion noExiste = assertThrows(NoEncontradoExcepcion.class, () -> {
                this.personaService.eliminarPersonaPorNombreUsuario("vsaavedrae12");
            });
            this.personaService.eliminarPersonaPorNombreUsuario("erickdp");
            assertEquals("No se han encontrado registros para: vsaavedrae12",
                    noExiste.getCausa());

            InOrder inOrder = inOrder(personaRepository);

            inOrder.verify(this.personaRepository).findPersonaByUsuario("vsaavedrae12");
            inOrder.verify(this.personaRepository).findPersonaByUsuario("erickdp");
        }
    }

    @Nested
    @DisplayName("Pruebas para los servicios de FormularioLiderazgo")
    class fLTest {

        @Mock
        private FormularioLiderazgoRepository formularioLiderazgoRepository;

        @InjectMocks
        private FormularioLiderazgoServiceImp formularioLiderazgoService;

        @Test
        void eliminarRespuestaFLTest() {
            // Given
            when(this.formularioLiderazgoRepository.findFormularioLiderazgoByUser("erickdp")).thenReturn(getFormularioLiderazgo001().orElse(null));

            // When
            this.formularioLiderazgoService.eliminarRespuestaFormularioLiderazgo("erickdp");

            NoEncontradoExcepcion noEncontradoExcepcion = assertThrows(NoEncontradoExcepcion.class, () -> {
                this.formularioLiderazgoService.eliminarRespuestaFormularioLiderazgo("vsaavedrae12");
            });

            // Then
            assertEquals(
                    "No se han encontrado registros para el usuario: vsaavedrae12",
                    noEncontradoExcepcion.getCausa()
                    );

            InOrder inOrder = inOrder(this.formularioLiderazgoRepository);

            inOrder.verify(this.formularioLiderazgoRepository).findFormularioLiderazgoByUser("erickdp");
            inOrder.verify(this.formularioLiderazgoRepository).findFormularioLiderazgoByUser("vsaavedrae12");
        }
    }

}
