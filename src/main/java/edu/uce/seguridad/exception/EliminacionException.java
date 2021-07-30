package edu.uce.seguridad.exception;

import lombok.Data;

@Data
public class EliminacionException extends RuntimeException {
    private String causa;

    public EliminacionException(String message, String causa) {
        super(message);
        this.causa = causa;
    }

    public EliminacionException(String message) {
        super(message);
    }

    public EliminacionException() {
    }
}
