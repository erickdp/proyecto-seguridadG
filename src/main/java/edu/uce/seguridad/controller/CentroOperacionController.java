package edu.uce.seguridad.controller;

import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.CentroOperacion;
import edu.uce.seguridad.service.service.CentroOperacionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/sgcnegocio/centros")
@AllArgsConstructor
public class CentroOperacionController {
    private CentroOperacionService service;

    /*
    ENDPOINT que busca todos los centros de operación de emergencias
    Ej:
    http://localhost:8080/sgcnegocio/centros
    - Si existen centros de operación se devuelve un estado 200 - Ok
    - En el caso de no encontrar ningún registro se devuelve un estado 404 - NOT FOUND
    - En el caso de fallar la BD se devuelve un estado 500 - INTERNAL SERVER ERROR
    * */
    @GetMapping
    public ResponseEntity<List<CentroOperacion>> listar() {
        List<CentroOperacion> centros = service.buscarTodos();
        return new ResponseEntity<List<CentroOperacion>>(centros, HttpStatus.OK);
    }

    /*
    ENDPOINT que busca todos los centros de operación de emergencias por un identificador, el id se debe enviar por la URL
    Ej:
    http://localhost:8080/sgcnegocio/centros/buscarCentroPorId/61133f919761987103770a49
    - Si existen centros de operación se devuelve un estado 200 - Ok
    - En el caso de no encontrar ningún registro se devuelve un estado 404 - NOT FOUND
    - En el caso de fallar la BD se devuelve un estado 500 - INTERNAL SERVER ERROR
    * */
    @GetMapping("/buscarCentroPorId/{id}")
    public ResponseEntity<CentroOperacion> listarPorId(@PathVariable("id") String idCentro) {
        CentroOperacion centro = service.buscaPorId(idCentro);
        if (centro == null) {
            throw new NoEncontradoExcepcion("respuesta", "Id no encontrado " + idCentro);
        }
        return new ResponseEntity<CentroOperacion>(centro, HttpStatus.OK);
    }

    /*
    ENDPOINT que busca todos los centros de operación de emergencias por usuario, el nombre de usuario se debe enviar por la URL
    Ej:
    http://localhost:8080/sgcnegocio/centros/buscarCentroPorUsuario/nombreUsuario
    - Si existen centros de operación se devuelve un estado 200 - Ok
    - En el caso de no encontrar ningún registro se devuelve un estado 404 - NOT FOUND
    - En el caso de fallar la BD se devuelve un estado 500 - INTERNAL SERVER ERROR
    * */
    @GetMapping("/buscarCentroPorUsuario/{usuario}")
    public ResponseEntity<?> buscarPorUsuario(@PathVariable("usuario") String nombreUsuario) {
        CentroOperacion centro = service.buscarCentroPorUsuario(nombreUsuario);
        return new ResponseEntity<>(centro, HttpStatus.OK);
    }

    /*
    ENDPOINT para guardar un centro de operación de emergencia
    Se debe enviar un JSON con el usuario, criterio de movilización, la lista de miembros, la lista de lugares de encuentro
    Ej:
    http://localhost:8080/sgcnegocio/centros
    JSON
    {
        "usuario": "nombreUsuario",
        "criteriosMovilizacion": "Sismo de 6 puntos según escala Ritcher",
        "miembros" : [
            {"funcion" : "Líder (y sustituto)", "departamento" : "Nombre departamento", "telefono" : "0999999999"},
            {"funcion" : "Análisis y planificación", "departamento" : "Nombre departamento", "telefono" : "0999999999"},
            {"funcion" : "Recolección de información", "departamento" : "Nombre departamento", "telefono" : "0999999999"},
            {"funcion" : "Operación in situ", "departamento" : "Nombre departamento", "telefono" : "0999999999"}
        ],
        "lugaresEncuentro" : [
            {"prioridad" : "1", "lugar" : "Lugar de encuentro", "direccion" : "Dirección", "telefono" : "0999999999"},
            {"prioridad" : "2", "lugar" : "Lugar de encuentro", "direccion" : "Dirección", "telefono" : "0999999999"},
            {"prioridad" : "3", "lugar" : "Lugar de encuentro", "direccion" : "Dirección", "telefono" : "0999999999"}
        ]
    }
    */
    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<Object> guardar(@RequestBody CentroOperacion centroEnviado) {
        CentroOperacion centro = service.agregar(centroEnviado);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}")
                .buildAndExpand(centro.get_id()).toUri();
        return ResponseEntity.created(location).build();
    }

    /*
    ENDPOINT para actualizar un centro de operación de emergencia
    Se debe mandar el id por la URL y los nuevos datos en formato JSON del centro de operaciones que se quiere actualizar
    Ej:
    http://localhost:8080/sgcnegocio/centros/61133f919761987103770a49
    JSON
    {
        "usuario": "nombreUsuario",
        "criteriosMovilizacion": "Sismo de 6 puntos según escala Ritcher",
        "miembros" : [
            {"funcion" : "Líder (y sustituto)", "departamento" : "Nombre departamento", "telefono" : "0999999999"},
            {"funcion" : "Análisis y planificación", "departamento" : "Nombre departamento", "telefono" : "0999999999"},
            {"funcion" : "Recolección de información", "departamento" : "Nombre departamento", "telefono" : "0999999999"},
            {"funcion" : "Operación in situ", "departamento" : "Nombre departamento", "telefono" : "0999999999"}
        ],
        "lugaresEncuentro" : [
            {"prioridad" : "1", "lugar" : "Lugar de encuentro", "direccion" : "Dirección", "telefono" : "0999999999"},
            {"prioridad" : "2", "lugar" : "Lugar de encuentro", "direccion" : "Dirección", "telefono" : "0999999999"},
            {"prioridad" : "3", "lugar" : "Lugar de encuentro", "direccion" : "Dirección", "telefono" : "0999999999"}
        ]
    }
    */
    @PutMapping("/{id}")
    public ResponseEntity<Object> modificar(@RequestBody CentroOperacion centroOperacion, @PathVariable("id") String id) {
        centroOperacion.set_id(id);
        service.actualizar(centroOperacion);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    /*
    ENDPOINT
    Se debe mandar unicamente el id en el URL cuando se va a eliminar un centro de operación
    Ej:
    http://localhost:8080/sgcnegocio/centros/61133f919761987103770a49
    */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminarPorUsuario(@PathVariable("id") String id) {
        service.eliminarDocumento(id);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
