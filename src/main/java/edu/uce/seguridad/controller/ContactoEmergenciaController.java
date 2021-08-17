package edu.uce.seguridad.controller;

import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.ContactoEmergencia;
import edu.uce.seguridad.service.service.ContactoEmergenciaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/sgcnegocio/contactos")
@AllArgsConstructor
public class ContactoEmergenciaController {
    private ContactoEmergenciaService service;

    /*
    ENDPOINT que busca todos los contactos de emergencia
    Ej:
    http://localhost:8080/sgcnegocio/contactos
    - Si existen contactos de emergencia se devuelve un estado 200 - Ok
    - En el caso de no encontrar ningún registro se devuelve un estado 404 - NOT FOUND
    - En el caso de fallar la BD se devuelve un estado 500 - INTERNAL SERVER ERROR
    * */
    @GetMapping
    public ResponseEntity<List<ContactoEmergencia>> listar() {
        List<ContactoEmergencia> contactos = service.buscarTodos();
        return new ResponseEntity<List<ContactoEmergencia>>(contactos, HttpStatus.OK);
    }

    /*
    ENDPOINT que busca todos los contactos de emergencia por un identificador, el id se debe enviar por la URL
    Ej:
    http://localhost:8080/sgcnegocio/contactos/buscarContactoPorId/61133f919761987103770a49
    - Si existen contactos de emergencia se devuelve un estado 200 - Ok
    - En el caso de no encontrar ningún registro se devuelve un estado 404 - NOT FOUND
    - En el caso de fallar la BD se devuelve un estado 500 - INTERNAL SERVER ERROR
    * */
    @GetMapping("/buscarContactoPorId/{id}")
    public ResponseEntity<ContactoEmergencia> listarPorId(@PathVariable("id") String idContacto) {
        ContactoEmergencia contacto = service.buscaPorId(idContacto);
        if (contacto == null) {
            throw new NoEncontradoExcepcion("respuesta", "Id no encontrado " + idContacto);
        }
        return new ResponseEntity<ContactoEmergencia>(contacto, HttpStatus.OK);
    }

    /*
    ENDPOINT que busca todos los contactos de emergencia por departamento, el nombre del departamento se debe enviar por la URL
    Ej:
    http://localhost:8080/sgcnegocio/contactos/buscarContactoPorDepartamento/nombreDepartamento
    - Si existen contactos se devuelve un estado 200 - Ok
    - En el caso de no encontrar ningún registro se devuelve un estado 404 - NOT FOUND
    - En el caso de fallar la BD se devuelve un estado 500 - INTERNAL SERVER ERROR
    * */
    @GetMapping("/buscarContactoPorDepartamento/{departamento}")
    public ResponseEntity<ContactoEmergencia> buscarPorDepartamento(@PathVariable("departamento") String nombreDepartamento) {
        ContactoEmergencia contacto = service.buscarContactoPorDepartamento(nombreDepartamento);
        return new ResponseEntity<ContactoEmergencia>(contacto, HttpStatus.OK);
    }

    /*
    ENDPOINT para guardar un contacto de emergencia
    Se debe enviar un JSON con el nombre del departamento, nombre del personal, teléfono, correo y el status
    Ej:
    http://localhost:8080/sgcnegocio/contactos
    JSON
    {
        "departamento": "Nombre departamento",
        "nombrePersonal": "Nombre y apellido"
        "telefono": "0999999999",
        "correo": "correo@gmail.com",
        "status": "Estatus"
    }
    */
    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<Object> guardar(@RequestBody ContactoEmergencia contactoEnviado) {
        ContactoEmergencia contacto = service.agregar(contactoEnviado);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}")
                .buildAndExpand(contacto.get_id()).toUri();
        return ResponseEntity.created(location).build();
    }

    /*
    ENDPOINT para actualizar un contacto de emergencia
    Se debe mandar el id por la URL y los nuevos datos en formato JSON del contacto que se quiere actualizar
    Ej:
    http://localhost:8080/sgcnegocio/contactos/61133f919761987103770a49
    JSON
    {
        "departamento": "Nombre departamento",
        "nombrePersonal": "Nombre y apellido"
        "telefono": "0999999999",
        "correo": "correo@gmail.com",
        "status": "Estatus"
    }
    */
    @PutMapping("/{id}")
    public ResponseEntity<Object> modificar(@RequestBody ContactoEmergencia contacto, @PathVariable(value = "id") String id) {
        contacto.set_id(id);
        ContactoEmergencia contactoUpdated = service.actualizar(contacto);
        return new ResponseEntity<Object>(contactoUpdated, HttpStatus.OK);
    }

    /*
    ENDPOINT
    Se debe mandar únicamente el id en el URL cuando se va a eliminar un contacto de emergencia
    Ej:
    http://localhost:8080/sgcnegocio/contactos/61133f919761987103770a49
    */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminarPorId(@PathVariable("id") String id) {
        service.eliminarDocumento(id);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
