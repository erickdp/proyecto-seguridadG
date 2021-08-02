package edu.uce.seguridad.data;

import edu.uce.seguridad.model.FormularioLiderazgo;

import java.util.Optional;

public class DatosFormularioLiderazgo {
    public static Optional<FormularioLiderazgo> getFormularioLiderazgo() {
        FormularioLiderazgo formularioLiderazgo = new FormularioLiderazgo();
        formularioLiderazgo.set_id("1");
        formularioLiderazgo.setUser("erickdp");
        formularioLiderazgo.setNegocio("negocio");
        formularioLiderazgo.setPersonal("personal");
        return Optional.of(formularioLiderazgo);
    }
}
