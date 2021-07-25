package edu.uce.seguridad.controller;

import edu.uce.seguridad.model.Persona;
import edu.uce.seguridad.model.Usuario;
import edu.uce.seguridad.repository.FormularioAlcanceRepository;
import edu.uce.seguridad.service.PersonaService;
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
@CrossOrigin(origins = {"https://seguridad-sgcn.herokuapp.com", "https://localhost:8080"},
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
@AllArgsConstructor
public class ControladoPrincipal {

    private PersonaService personaService;

    private FormularioAlcanceRepository formularioAlcanceRepository;

    /*
    ENDPOINT activo
    Se debe de enviar un JSON con el nombre de usuario y contrasena
    Ej:
    {
    "nombreUsuario": "salpalal57",
    "contrasena": "13EPL"
    }

    - Si se encuentra el usuario se devuelve un estado 302 - FOUND
    - En el caso de no encontrar ningun registro se devuelve un estado 404 - NOT FOUND
    - En el caso de fallar la BD se devuelve un estado 500 - INTERNAL SERVER ERROR
    * */
    @PostMapping("/iniciarSesion")
    public ResponseEntity<?> iniciarSesion(
            @RequestBody Usuario usuario) {
        Persona persona = null;
        Map<String, Object> response = new HashMap<>();

        try {
            persona = this.personaService.buscarPersonaPorUsuario(
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
    - Si se encuentra la persona se devuelve un estado 302 - FOUND
    - En el caso de no encontrar ningun registro se devuelve un estado 404 - NOT FOUND
    - En el caso de fallar la BD se devuelve un estado 500 - INTERNAL SERVER ERROR
    * */
    @GetMapping("/buscarPorIdentificador/{identificador}")
    public ResponseEntity<?> buscarPorIdentificador(
            @PathVariable(value = "identificador") String objectId) {
        Persona persona = null;
        Map<String, Object> response = new HashMap<>();
        try {
            persona = this.personaService.buscaPorId(objectId);
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
        return new ResponseEntity<Persona>(persona, HttpStatus.FOUND);
    }

    /*
    ENDPOINT que devuelve a todos los miembros de una determinada organizacion, el parametro
    a enviar debe de ser la organizacion
    - Si se encuentra las personas se devuelve un estado 302 - FOUND
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
        return new ResponseEntity<List<Persona>>(personas, HttpStatus.FOUND);
    }

}
