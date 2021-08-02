package edu.uce.seguridad.data;

import edu.uce.seguridad.model.Persona;
import edu.uce.seguridad.model.Usuario;

import java.util.Optional;

public class DatosPersona {
    public static Optional<Persona> getPersona001() {
        Persona persona = new Persona();
        Usuario usuario = new Usuario(null, "erickdp", "1234", "REPRESENTANTE");
        persona.set_id("1");
        persona.setOrganizacion("UNIVERSIDAD CENTRAL DEL ECUADOR");
        persona.setUsuario(usuario);
        persona.setNombre("Erick");
        persona.setApellido("Diaz");
        return Optional.of(persona);
    }
}
