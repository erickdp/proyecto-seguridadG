package edu.uce.seguridad.exception;

/*
 Clase que sirve para manejar excepciones al eliminar una persona,
 se puede reutilizar
* */
public class MiClaseException extends RuntimeException {
    public MiClaseException() {
    }

    public MiClaseException(String message) {
        super(message);
    }
}
