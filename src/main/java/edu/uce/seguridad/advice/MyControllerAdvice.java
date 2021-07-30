package edu.uce.seguridad.advice;

import edu.uce.seguridad.exception.EliminacionException;
import edu.uce.seguridad.exception.MiClaseException;
import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/*
Esta clase ayuda a capturar todos los errores en los controllers, generando alta cohesion
* */
@ControllerAdvice
public class MyControllerAdvice {

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Map<String, String>> handleDataAccesException(
            DataAccessException dae
    ) {
        Map<String, String> response = new HashMap<>();
        response.put("respuesta", "Error producido en la base de datos, ponganse en cotacto.");
        response.put("causa", dae.getMessage().
                concat(": ").concat(dae.getMostSpecificCause().getMessage()));
        return new ResponseEntity<Map<String, String>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MiClaseException.class)
    public ResponseEntity<Map<String, String>> handleMiClaseException(
            MiClaseException mce
    ) {
        Map<String, String> response = new HashMap<>();
        response.put(mce.getMessage(), mce.getCausa());
        return new ResponseEntity<Map<String, String>>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoEncontradoExcepcion.class)
    public ResponseEntity<Map<String, String>> handleNoEncontradoExcepcion(
            NoEncontradoExcepcion nee
    ) {
        Map<String, String> response = new HashMap<>();
        response.put(nee.getMessage(), nee.getCausa());
        return new ResponseEntity<Map<String, String>>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EliminacionException.class)
    public ResponseEntity<Map<String, String>> handleEliminacionException(
            EliminacionException ee
    ) {
        Map<String, String> response = new HashMap<>();
        response.put(ee.getMessage(), ee.getCausa());
        return new ResponseEntity<Map<String, String>>(response, HttpStatus.EXPECTATION_FAILED);
    }

}
