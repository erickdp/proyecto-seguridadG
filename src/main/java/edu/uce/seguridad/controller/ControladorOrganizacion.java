package edu.uce.seguridad.controller;

import edu.uce.seguridad.model.Organizacion;
import edu.uce.seguridad.service.service.OrganizacionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sgcnegocio/organizacion")
@AllArgsConstructor
public class ControladorOrganizacion {

    private OrganizacionService organizacionService;

    /*
    ENDPOINT activo.
    Devuelve todas las organizaciones

    - Si se encuentra la organizacion se devuelve un estado 200 - Ok
    - En el caso de no encontrar ningun registro se devuelve un estado 404 - NOT FOUND
    - En el caso de fallar la BD se devuelve un estado 500 - INTERNAL SERVER ERROR
    * */
    @GetMapping("/listarOrganizaciones")
    public ResponseEntity<?> listarOrganizaciones() {
        List<Organizacion> organizaciones = this.organizacionService.buscarTodos();
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
        Organizacion organizacionN = this.organizacionService.agregar(organizacion);
        return new ResponseEntity<Organizacion>(organizacionN, HttpStatus.CREATED);
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
        Organizacion organizacionN = this.organizacionService.actualizar(organizacion);
        return new ResponseEntity<Organizacion>(organizacion, HttpStatus.OK);
    }

    /*
    ENDPOINT ACTIVO

    Elimina la organizacion mediante su nombre esto porque es unico. Solo enviar
    en la URI el nombre de la organizacion

    - Si no se elimina la organizacon se devuelve un estado 417 - EXPECTATION_FAILED
    - En el caso de fallar la BD se devuelve un estado 500 - INTERNAL SERVER ERROR
    - Si se elimina la organizacion se devuelve un estado 200 - OK
    * */
    @DeleteMapping("/eliminarOrganizacion/{nombreOrganizacion}")
    public ResponseEntity<?> eliminarUsuario(
            @PathVariable(value = "nombreOrganizacion") String organizacion
    ) {
        this.organizacionService.eliminarPorNombreOrganizacion(organizacion);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }

    /*
    ENDPOINT ACTIVO

    Busca la organizacion mediante su nombre esto porque es unico. Solo enviar
    en la URI el nombre de la organizacion

    @param nombreOrganizacion

    - Si no se encuentra la organizacon se devuelve un estado 404 - NOT_FOUND
    - En el caso de fallar la BD se devuelve un estado 500 - INTERNAL SERVER ERROR
    - Si se encuentra la organizacion se devuelve un estado 200 - OK
    * */
    @GetMapping("/buscarOrganizacion/{nombreOrganizacion}")
    public ResponseEntity<?> buscarOrganizacion(@PathVariable String nombreOrganizacion) {
        Organizacion organizacion = this.organizacionService.buscarPorNombreOrganizacion(
                nombreOrganizacion
        );
        return new ResponseEntity<Organizacion>(organizacion, HttpStatus.OK);
    }
}
