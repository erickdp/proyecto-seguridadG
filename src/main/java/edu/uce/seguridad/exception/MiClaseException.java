package edu.uce.seguridad.exception;

import lombok.Data;

/*
 Clase que sirve para manejar excepciones al eliminar una persona,
 se puede reutilizar
* */
@Data
public class MiClaseException extends RuntimeException {
    private String causa;
    public MiClaseException() {
    }

    public MiClaseException(String message) {
        super(message);
    }

    public MiClaseException(String message, String causa) {
        super(message);
        this.causa = causa;
    }
}
