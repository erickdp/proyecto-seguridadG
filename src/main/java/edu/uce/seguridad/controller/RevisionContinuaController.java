package edu.uce.seguridad.controller;

import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.RevisionContinua;
import edu.uce.seguridad.service.service.RevisionContinuaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/sgcnegocio/revisiones")
@AllArgsConstructor
public class RevisionContinuaController {

    private RevisionContinuaService service;

    /*
    ENDPOINT que busca todas las revisiones continuas
    Ej:
    http://localhost:8080/sgcnegocio/revisiones
    - Si existen revisiones se devuelve un estado 200 - Ok
    - En el caso de no encontrar ningún registro se devuelve un estado 404 - NOT FOUND
    - En el caso de fallar la BD se devuelve un estado 500 - INTERNAL SERVER ERROR
    * */
    @GetMapping
    public ResponseEntity<List<RevisionContinua>> listar() {
        List<RevisionContinua> revisionesContinua = service.buscarTodos();
        return new ResponseEntity<List<RevisionContinua>>(revisionesContinua, HttpStatus.OK);
    }

    /*
    ENDPOINT que busca todas las revisiones continuas por un identificador, el id se debe enviar por la URL
    Ej:
    http://localhost:8080/sgcnegocio/revisiones/buscarRevisionPorId/61133f919761987103770a49
    - Si existen revisiones se devuelve un estado 200 - Ok
    - En el caso de no encontrar ningún registro se devuelve un estado 404 - NOT FOUND
    - En el caso de fallar la BD se devuelve un estado 500 - INTERNAL SERVER ERROR
    * */
    @GetMapping("/buscarRevisionPorId/{id}")
    public ResponseEntity<RevisionContinua> listarPorId(@PathVariable("id") String idRevision) {
        RevisionContinua revision = service.buscaPorId(idRevision);
        if (revision == null) {
            throw new NoEncontradoExcepcion("respuesta", "Id no encontrado " + idRevision);
        }
        return new ResponseEntity<RevisionContinua>(revision, HttpStatus.OK);
    }

    /*
    ENDPOINT que busca la revisión continua por usuario, el nombre de usuario se debe enviar por la URL
    Ej:
    http://localhost:8080/sgcnegocio/revisiones/buscarRevisionPorUsuario/nombreUsuario
    - Si existen revisiones se devuelve un estado 200 - Ok
    - En el caso de no encontrar ningún registro se devuelve un estado 404 - NOT FOUND
    - En el caso de fallar la BD se devuelve un estado 500 - INTERNAL SERVER ERROR
    * */
    @GetMapping("/buscarRevisionPorUsuario/{usuario}")
    public ResponseEntity<?> buscarPorUsuario(@PathVariable("usuario") String nombreUsuario) {
        RevisionContinua revision = service.buscarRevisionPorUsuario(nombreUsuario);
        return new ResponseEntity<>(revision, HttpStatus.OK);
    }

    /*
    ENDPOINT para guardar una revisión continua
    Se debe enviar un JSON con el nombre del usuario y un mapa con los temas a revisar
    Ej:
    http://localhost:8080/sgcnegocio/revisiones
    JSON
    {
        "usuario": "Usuario",
        "temas": {
            "Marco teórico del PCN, Propósito, Alcance y equipo": [{
                "formulario": "Estructura del PCN",
                "estado": "En curso",
                "cambios": "Cambios en el entorno de negocio",
                "temasRevisar": "Temas a revisar"
            }],
            "Actividades Prioritarias y Tiempos de Recuperación Ideales": [{
                "formulario": "Tabla comparativa de niveles de impacto",
                "estado": "En curso",
                "cambios": "Cambios en el entorno de negocio",
                "temasRevisar": "Temas a revisar"
            }, {
                "formulario": "Período Máximo Tolerable de Interrupción",
                "estado": "En curso",
                "cambios": "Cambios en el entorno de negocio",
                "temasRevisar": "Temas a revisar"
            }, {
                "formulario": "Actividades Prioritarias y Tiempos Ideales de Recuperación",
                "estado": "En curso",
                "cambios": "Cambios en el entorno de negocio",
                "temasRevisar": "Temas a revisar"
            }]
        }
    }
    */
//    @PostMapping(produces = "application/json", consumes = "application/json")
//    public ResponseEntity<Object> guardar(@RequestBody RevisionContinua revisionEnviada) {
//        RevisionContinua revision = service.agregar(revisionEnviada);
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}")
//                .buildAndExpand(revision.get_id()).toUri();
//        return ResponseEntity.created(location).build();
//    }

    /*
    ENDPOINT para actualizar una revisión continua
    Se debe mandar el id por la URL y los nuevos datos  de la revisión que se quiere actualizar
    Ej:
    http://localhost:8080/sgcnegocio/revisiones/61133f919761987103770a49
    JSON
    {
        "usuario": "Usuario",
        "temas": {
            "Marco teórico del PCN, Propósito, Alcance y equipo": [{
                "formulario": "Estructura del PCN",
                "estado": "En curso",
                "cambios": "Cambios en el entorno de negocio",
                "temasRevisar": "Temas a revisar"
            }],
            "Actividades Prioritarias y Tiempos de Recuperación Ideales": [{
                "formulario": "Tabla comparativa de niveles de impacto",
                "estado": "En curso",
                "cambios": "Cambios en el entorno de negocio",
                "temasRevisar": "Temas a revisar"
            }, {
                "formulario": "Período Máximo Tolerable de Interrupción",
                "estado": "En curso",
                "cambios": "Cambios en el entorno de negocio",
                "temasRevisar": "Temas a revisar"
            }, {
                "formulario": "Actividades Prioritarias y Tiempos Ideales de Recuperación",
                "estado": "En curso",
                "cambios": "Cambios en el entorno de negocio",
                "temasRevisar": "Temas a revisar"
            }]
        }
    }
    */
    @PutMapping("/{id}")
    public ResponseEntity<Object> modificar(@RequestBody RevisionContinua revision, @PathVariable("id") String id) {
        revision.set_id(id);
        service.actualizar(revision);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    /*
    ENDPOINT
    Se debe mandar únicamente el id en el URL cuando se va a eliminar una revisión continua
    Ej:
    http://localhost:8080/sgcnegocio/revisiones/61133f919761987103770a49
    */
    @DeleteMapping("/{usuario}")
    public ResponseEntity<Object> eliminarPorUsuario(@PathVariable("usuario") String nombreUsuario) {
        service.eliminarDocumento(nombreUsuario);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
