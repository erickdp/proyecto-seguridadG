package edu.uce.seguridad.controller;

import edu.uce.seguridad.exception.MiClaseException;
import edu.uce.seguridad.model.Organizacion;
import edu.uce.seguridad.service.service.OrganizacionService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sgcnegocio/organizacion")
@CrossOrigin(origins = {"http://localhost:80**", "http://localhost:3***"},
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@AllArgsConstructor
public class ControladorOrganizacion {

    private OrganizacionService organizacionService;

    /*
    ENDPOINT activo.
    Devuelve todas las organizaciones

    - Si se encuentra el usuario se devuelve un estado 200 - Ok
    - En el caso de no encontrar ningun registro se devuelve un estado 404 - NOT FOUND
    - En el caso de fallar la BD se devuelve un estado 500 - INTERNAL SERVER ERROR
    * */
    @GetMapping("/listarOrganizaciones")
    public ResponseEntity<?> listarOrganizaciones() {
        List<Organizacion> organizaciones = null;
        Map<String, Object> response = new HashMap<>();

        try {
            organizaciones = this.organizacionService.buscarTodos();
        } catch (DataAccessException dae) {
            response.put("respuesta", "Error producido en la base de datos, ponganse en cotacto.");
            response.put("causa", dae.getMessage().
                    concat(": ").concat(dae.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (organizaciones.isEmpty()) {
            response.put("respuesta", "No existen registros de organizaciones");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Organizacion>>(organizaciones, HttpStatus.OK);
    }

    /*
    ENDPOINT activo
    Se debe enviar un JSON con nombre de la organizacion y un tipo de contacto, sea tlf o mail
    no es obligatorio
    Ej:
    {
    "organizacion": "Universidad Central Del Ecuador",
    "departamento": [
        "TI",
        "RECURSOS HUMANOS"
    ],
    "contacto": "uce@mail.com"
    }

    - Si la operacion de insercion tiene exito se devuelve el registro y estado 201
    - En el caso de fallar la BD se devuelve un estado 500 - INTERNAL SERVER ERROR
    * */
    @PostMapping("/agregarOrganizacion")
    public ResponseEntity<?> agregarOrganizacion(
            @RequestBody Organizacion organizacion) {
        Organizacion organizacionN = null;
        Map<String, Object> response = new HashMap<>();

        try {
            organizacionN = this.organizacionService.agregar(organizacion);
        } catch (DataAccessException dae) {
            response.put("respuesta", dae.getMessage()
                    .concat(": ").concat(dae.getMostSpecificCause().getMessage()));
            response.put("mensaje", "Hubo un error al ingresar nuevos datos. Pongase en contacto");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("respuesta", "Se a agregado correctamente");
        response.put("organizacion", organizacionN);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    /*
    ENDPOINT activo
    Se debe enviar un JSON con nombre de la organizacion y un tipo de contacto, sea tlf o mail
    no es obligatorio y tambien el id
    Ej:
    {
    "_id": 610088dc045a372208adc3ae,
    "nombreOrganizacion": "Universidad Central Del Ecuador",
    "departamento": [
        "TI",
        "RECURSOS HUMANOS"
    ],
    "contacto": "uce@mail.com"
    }

    - Si la operacion de actualizacion tiene exito se devuelve el registro y estado 201
    - En el caso de fallar la BD se devuelve un estado 500 - INTERNAL SERVER ERROR
    * */
    @PutMapping("/actualizarOrganizacion")
    public ResponseEntity<?> actualizarOrganizacion(
            @RequestBody Organizacion organizacion) {
        Organizacion organizacionN = null;
        Map<String, Object> response = new HashMap<>();

        try {
            organizacionN = this.organizacionService.actualizarOrganizacion(organizacion);
        } catch (DataAccessException dae) {
            response.put("respuesta", dae.getMessage()
                    .concat(": ").concat(dae.getMostSpecificCause().getMessage()));
            response.put("mensaje", "Hubo un error al actualizar datos. Pongase en contacto");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (MiClaseException mce) {
            response.put("respuesta", mce.getMessage());
            response.put("mensaje", "Error a actualizar la información");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        response.put("respuesta", "Se a actualizado correctamente");
        response.put("organizacion", organizacionN);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    /*
    ENDPOINT ACTIVO

    Elimina la organizacion mediante su nombre esto porque es unico. Solo enviar
    en la URI el nombre de la organizacion

    - Si no se elimina la organizacon se devuelve un estado 417 - EXPECTATION_FAILED
    - En el caso de fallar la BD se devuelve un estado 500 - INTERNAL SERVER ERROR
    - Si se elimina la persona se devuelve un estado 200 - OK
    * */
    @DeleteMapping("/eliminarOrganizacion/{nombreOrganizacion}")
    public ResponseEntity<?> eliminarUsuario(
            @PathVariable(value = "nombreOrganizacion") String organizacion
    ) {
        Map<String, Object> response = new HashMap<>();
        String respuesta = "";

        try {
            respuesta = this.organizacionService.eliminarPorNombreOrganizacion(organizacion);
        } catch (DataAccessException dae) {
            response.put("respuesta", "Error al encontrar la información.");
            response.put("mensaje",
                    dae.getMessage().concat(": ").concat(dae.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (MiClaseException mce) {
            response.put("respuesta", "No se a podido realizar la transacción.");
            response.put("mensaje", mce.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.EXPECTATION_FAILED);
        }

        response.put("respuesta", respuesta);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
}
