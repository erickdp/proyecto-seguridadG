package edu.uce.seguridad.controller;

import edu.uce.seguridad.model.Persona;
import edu.uce.seguridad.model.Usuario;
import edu.uce.seguridad.service.service.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        Persona persona = this.personaService.buscarPersonaPorUsuarioYContrasena(
                usuario.getNombreUsuario(),
                usuario.getContrasena());
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
        Persona personaN = this.personaService.agregar(persona);
        return new ResponseEntity<Persona>(personaN, HttpStatus.CREATED);
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
        Persona persona = this.personaService.buscaPorId(identificadorUsuario);
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
        List<Persona> personas = this.personaService.buscarPersonaPorOrganizacion(org);
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
        List<Persona> personas = this.personaService.buscarPersonaPorRol(role);
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
        List<Persona> personas = this.personaService.buscarPersonaPorRoleYOrganizacion(role, organizacion);
        return new ResponseEntity<List<Persona>>(personas, HttpStatus.OK);
    }

    /*
    ENDPOINT ACTIVO

    Elimina al usuario mediante su nombre de usuario, esto porque es unico. Solo enviar
    en la URI el nombre del Usuario

    En el caso de que este usuario tenga formularios, estos serian eliminados en cadena


    IRA INCREMENTANDO CON EL TIEMPO LA ELIMINACION EN CADENA

    - Si no se elimina la persona se devuelve un estado 417 - EXPECTATION_FAILED
    - En el caso de fallar la BD se devuelve un estado 500 - INTERNAL SERVER ERROR
    - Si se elimina la persona se devuelve un estado 202 - ACCEPTED
    * */
    @DeleteMapping("/eliminarUsuario/{nombreUsuario}")
    public ResponseEntity<Void> eliminarUsuario(
            @PathVariable(value = "nombreUsuario") String usuario
    ) {
        this.personaService.eliminarPersonaPorNombreUsuario(usuario);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
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
        List<Persona> personas = this.personaService.buscarPersonasPorOrganizacionYDepartamento(
                org, depar);
        return new ResponseEntity<List<Persona>>(personas, HttpStatus.OK);
    }
}
