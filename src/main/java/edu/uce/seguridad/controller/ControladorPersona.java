package edu.uce.seguridad.controller;

import edu.uce.seguridad.exception.MiClaseException;
import edu.uce.seguridad.model.Persona;
import edu.uce.seguridad.model.Usuario;
import edu.uce.seguridad.service.service.PersonaService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sgcnegocio")
@AllArgsConstructor
public class ControladorPersona {

    private PersonaService personaService;

    /*
    ENDPOINT activo
    Se debe de enviar un JSON con el nombre de usuario y contrasena
    Ej:
    {
    "nombreUsuario": "salpalal57",
    "contrasena": "13EPL"
    }

    - Si se encuentra el usuario se devuelve un estado 200 - Ok
    - En el caso de no encontrar ningun registro se devuelve un estado 404 - NOT FOUND
    - En el caso de fallar la BD se devuelve un estado 500 - INTERNAL SERVER ERROR
    * */
    @PostMapping("/iniciarSesion")
    public ResponseEntity<?> iniciarSesion(
            @RequestBody Usuario usuario) {
        Persona persona = null;
        Map<String, Object> response = new HashMap<>();

        try {
            persona = this.personaService.buscarPersonaPorUsuarioYContrasena(
                    usuario.getNombreUsuario(),
                    usuario.getContrasena());
        } catch (DataAccessException dae) {
            response.put("respuesta", "Error producido en la base de datos, ponganse en cotacto.");
            response.put("causa", dae.getMessage().
                    concat(": ").concat(dae.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (persona == null) {
            response.put("respuesta", "Error en las credenciales ingresadas, pruebe de nuevo.");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Persona>(persona, HttpStatus.OK);
    }

    /*
    ENDPOINT activo
    Se debe enviar un JSON con nombre, apellido, organizacion, departamento, rol de usuario
    Ej:
    {
    "nombre": "Samantha",
    "apellido": "Jara",
    "organizacion": "UCE",
    "departamento": "INGENIERIA",
    "usuario": {
        "role": "REPRESENTANTE"
    }
    }

    - Si la operacion de insercion tiene exito se devuelve el registro y estado 201
    - En el caso de fallar la BD se devuelve un estado 500 - INTERNAL SERVER ERROR
    * */
    @PostMapping("/agregarUsuario")
    public ResponseEntity<?> agregarUsuario(
            @RequestBody Persona persona) {
        Persona personaN = null;
        Map<String, Object> response = new HashMap<>();

        try {
            personaN = this.personaService.agregar(persona);
        } catch (DataAccessException dae) {
            response.put("respuesta", dae.getMessage()
                    .concat(": ").concat(dae.getMostSpecificCause().getMessage()));
            response.put("mensaje", "Hubo un error al ingresar nuevos datos. Pongase en contacto");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("respuesta", "Se a agregado correctamente");
        response.put("persona", personaN);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    /*
    ENDPOINT que busca por identificador a una persona en concreto, el id se debe de enviar
    por la URL.
    - Si se encuentra la persona se devuelve un estado 200 - Ok
    - En el caso de no encontrar ningun registro se devuelve un estado 404 - NOT FOUND
    - En el caso de fallar la BD se devuelve un estado 500 - INTERNAL SERVER ERROR
    * */
    @GetMapping("/buscarPorIdentificador/{identificador}")
    public ResponseEntity<?> buscarPorIdentificador(
            @PathVariable(value = "identificador") String identificadorUsuario) {
        Persona persona = null;
        Map<String, Object> response = new HashMap<>();
        try {
            persona = this.personaService.buscaPorId(identificadorUsuario);
        } catch (DataAccessException dae) {
            response.put("respuesta", "Error al encontrar la información.");
            response.put("mensaje",
                    dae.getMessage().concat(": ").concat(dae.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (persona == null) {
            response.put("respuesta", "No se ha encontrado ningun registro.");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Persona>(persona, HttpStatus.OK);
    }

    /*
    ENDPOINT que devuelve a todos los miembros de una determinada organizacion, el parametro
    a enviar debe de ser la organizacion
    - Si se encuentra las personas se devuelve un estado 200 - Ok
    - En el caso de no encontrar a las personas se devuelve un estado 404 - NOT FOUND
    - En el caso de fallar la BD se devuelve un estado 500 - INTERNAL SERVER ERROR
    * */
    @GetMapping("/buscarMiembrosOrganizacion/{nombreOrganizacion}")
    public ResponseEntity<?> buscarMiembrosOrganizacion(
            @PathVariable(value = "nombreOrganizacion") String org
    ) {
        List<Persona> personas = null;
        Map<String, Object> response = new HashMap<>();

        try {
            personas = this.personaService.buscarPersonaPorOrganizacion(org);
        } catch (DataAccessException dae) {
            response.put("respuesta", "Error al encontrar la información.");
            response.put("mensaje",
                    dae.getMessage().concat(": ").concat(dae.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (personas.isEmpty()) {
            response.put("respuesta", "No se han encontrado registros para: "
                    .concat(org));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Persona>>(personas, HttpStatus.OK);
    }

    /*
    ENDPOINT que devuelve a todos los miembros de un determinado ROLE, el parametro
    a enviar debe de ser el tipo de role
    - Si se encuentra las personas se devuelve un estado 200 - Ok
    - En el caso de no encontrar a las personas se devuelve un estado 404 - NOT FOUND
    - En el caso de fallar la BD se devuelve un estado 500 - INTERNAL SERVER ERROR
    * */
    @GetMapping("/buscarUsuariosPorRol/{tipoRole}")
    public ResponseEntity<?> buscarUsuariosPorRol(
            @PathVariable(value = "tipoRole") String role
    ) {
        List<Persona> personas = null;
        Map<String, Object> response = new HashMap<>();
        try {
            personas = this.personaService.buscarPersonaPorRol(role);
        } catch (DataAccessException dae) {
            response.put("respuesta", "Error al encontrar la información.");
            response.put("mensaje",
                    dae.getMessage().concat(": ").concat(dae.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (personas.isEmpty()) {
            response.put("respuesta", "No se han encontrado registros para: "
                    .concat(role));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Persona>>(personas, HttpStatus.OK);
    }

    /*
    ENDPOINT que devuelve a todos los miembros de un determinado ROLE pertenecientes a una ORGANIZACION, el parametro
    a enviar debe de ser el tipo de role y el nombre de la organizacion
    - Si se encuentra las personas se devuelve un estado 200 - Ok
    - En el caso de no encontrar a las personas se devuelve un estado 404 - NOT FOUND
    - En el caso de fallar la BD se devuelve un estado 500 - INTERNAL SERVER ERROR
    * */
    @GetMapping("/buscarUsuariosPorRolYOrganizacion/{tipoRole}/{nombreOrganizacion}")
    public ResponseEntity<?> buscarUsuariosPorRol(
            @PathVariable(value = "tipoRole") String role,
            @PathVariable(value = "nombreOrganizacion") String organizacion
    ) {
        List<Persona> personas = null;
        Map<String, Object> response = new HashMap<>();
        try {
            personas = this.personaService.buscarPersonaPorRoleYOrganizacion(role, organizacion);
        } catch (DataAccessException dae) {
            response.put("respuesta", "Error al encontrar la información.");
            response.put("mensaje",
                    dae.getMessage().concat(": ").concat(dae.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (personas.isEmpty()) {
            response.put("respuesta", "No se han encontrado registros para: "
                    .concat(role).concat(" en ").concat(organizacion));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Persona>>(personas, HttpStatus.OK);
    }

    /*
    ENDPOINT ACTIVO

    Elimina al usuario mediante su nombre de usuario, esto porque es unico. Solo enviar
    en la URI el nombre del Usuario

    - Si no se elimina la persona se devuelve un estado 417 - EXPECTATION_FAILED
    - En el caso de fallar la BD se devuelve un estado 500 - INTERNAL SERVER ERROR
    - Si se elimina la persona se devuelve un estado 200 - OK
    * */
    @DeleteMapping("/eliminarUsuario/{nombreUsuario}")
    public ResponseEntity<?> eliminarUsuario(
            @PathVariable(value = "nombreUsuario") String usuario
    ) {
        Map<String, Object> response = new HashMap<>();
        String respuesta = "";

        try {
            respuesta = this.personaService.eliminarPersonaPorNombreUsuario(usuario);
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

    /*
    ENDPOINT que devuelve a todos los miembros de un determinado DEPARTAMENTO
    pertenecientes a una ORGANIZACION, el parametro
    a enviar debe de ser el departamento y el nombre de la organizacion

    - Si se encuentra las personas se devuelve un estado 200 - Ok
    - En el caso de no encontrar a las personas se devuelve un estado 404 - NOT FOUND
    - En el caso de fallar la BD se devuelve un estado 500 - INTERNAL SERVER ERROR
    * */
    @GetMapping("/buscarUsuariosPorOrgYDep/{organizacion}/{departamento}")
    public ResponseEntity<?> buscarUsuariosPorOrgYDep(
            @PathVariable(value = "organizacion") String org,
            @PathVariable(value = "departamento") String depar
    ) {
        List<Persona> personas = null;
        Map<String, Object> response = new HashMap<>();
        try {
            personas = this.personaService.buscarPersonasPorOrganizacionYDepartamento(
                    org, depar);
        } catch (DataAccessException dae) {
            response.put("respuesta", "Error al encontrar la información.");
            response.put("mensaje",
                    dae.getMessage().concat(": ").concat(dae.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (personas.isEmpty()) {
            response.put("respuesta", "No se han encontrado registros para: "
                    .concat(depar).concat(" en ").concat(org));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Persona>>(personas, HttpStatus.OK);
    }
}
