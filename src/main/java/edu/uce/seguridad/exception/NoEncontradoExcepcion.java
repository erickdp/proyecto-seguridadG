package edu.uce.seguridad.exception;

import lombok.Data;

@Data
public class NoEncontradoExcepcion extends RuntimeException {
    private String causa;

    public NoEncontradoExcepcion() {
    }

    public NoEncontradoExcepcion(String message) {
        super(message);
    }

    public NoEncontradoExcepcion(String message, String causa) {
        super(message);
        this.causa = causa;
    }
}
