package edu.uce.seguridad.data;

import edu.uce.seguridad.model.Organizacion;

import java.util.Arrays;
import java.util.Optional;

public class DatosOrganizacion {
    public static Optional<Organizacion> getOrganizacion001() {
        Organizacion organizacion = new Organizacion();
        organizacion.set_id("1");
        organizacion.setOrganizacion("UNIVERSIDAD CENTRAL DEL ECUADOR");
        organizacion.setDepartamentos(Arrays.asList("TI", "RECURSOS HUMANOS", "INVESTIGACIÓN"));
        organizacion.setContacto("uce@mail.com");
        return Optional.of(organizacion);
    }

    public static Optional<Organizacion> getOrganizacion002() {
        Organizacion organizacion = new Organizacion();
        organizacion.set_id("2");
        organizacion.setOrganizacion("Autoridad de Tránsito Municipal");
        organizacion.setDepartamentos(Arrays.asList("TI", "RECURSOS HUMANOS"));
        organizacion.setContacto("atm@mail.com");
        return Optional.of(organizacion);
    }
}
