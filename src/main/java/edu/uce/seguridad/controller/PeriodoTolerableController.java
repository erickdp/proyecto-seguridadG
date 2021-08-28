package edu.uce.seguridad.controller;

import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.PeriodoTolerable;
import edu.uce.seguridad.service.service.PeriodoTolerableService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/sgcnegocio/periodos")
@AllArgsConstructor
public class PeriodoTolerableController {
    private PeriodoTolerableService service;

    /*
    ENDPOINT que busca todos los períodos
    Ej:
    http://localhost:8080/sgcnegocio/periodos
    - Si existen períodos se devuelve un estado 200 - Ok
    - En el caso de no encontrar ningún registro se devuelve un estado 404 - NOT FOUND
    - En el caso de fallar la BD se devuelve un estado 500 - INTERNAL SERVER ERROR
    * */
    @GetMapping
    public ResponseEntity<List<PeriodoTolerable>> listar() {
        List<PeriodoTolerable> periodo = service.buscarTodos();
        return new ResponseEntity<List<PeriodoTolerable>>(periodo, HttpStatus.OK);
    }

    /*
    ENDPOINT que busca todos los períodos por un identificador, el id se debe enviar por la URL
    Ej:
    http://localhost:8080/sgcnegocio/periodos/buscarPeriodoPorId/61133f919761987103770a49
    - Si existen actividades se devuelve un estado 200 - Ok
    - En el caso de no encontrar ningún registro se devuelve un estado 404 - NOT FOUND
    - En el caso de fallar la BD se devuelve un estado 500 - INTERNAL SERVER ERROR
    * */

    @GetMapping("/buscarPeriodoPorId/{id}")
    public ResponseEntity<PeriodoTolerable> listarPorId(@PathVariable("id") String idPeriodo) {
        PeriodoTolerable periodo = service.buscaPorId(idPeriodo);
        if (periodo == null) {
            throw new NoEncontradoExcepcion("respuesta", "Id no encontrado " + idPeriodo);
        }
        return new ResponseEntity<PeriodoTolerable>(periodo, HttpStatus.OK);
    }

    /*
    ENDPOINT que busca todos los períodos por usuario, el nombre de usuario se debe enviar por la URL
    Ej:
    http://localhost:8080/sgcnegocio/periodos/buscarPeriodosPorUsuario/nombre
    - Si existen períodos se devuelve un estado 200 - Ok
    - En el caso de no encontrar ningún registro se devuelve un estado 404 - NOT FOUND
    - En el caso de fallar la BD se devuelve un estado 500 - INTERNAL SERVER ERROR
    * */

    @GetMapping("/buscarPeriodosPorUsuario/{usuario}")
    public ResponseEntity<List<PeriodoTolerable>> buscarPorUsuario(@PathVariable("usuario") String nombreUsuario) {
        List<PeriodoTolerable> periodos = service.buscarPeriodosPorUsuario(nombreUsuario);
        return new ResponseEntity<List<PeriodoTolerable>>(periodos, HttpStatus.OK);
    }

    /*
    ENDPOINT para guardar una actividad prioritaria
    Se debe enviar un JSON con el nombre del producto/servicio, el período máximo tolerable de interrupción y el usuario
    Ej:
    http://localhost:8080/sgcnegocio/periodos
    JSON
    {
        "producto": "Producto/Servicio A",
        "pmti": "3 días",
        "tiempoIdealRecuperacion": "2 semanas",
        "usuario": "nombre"
    }
    */

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<Object> guardar(@RequestBody PeriodoTolerable periodoEnviado) {
        PeriodoTolerable periodo = service.agregar(periodoEnviado);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}")
                .buildAndExpand(periodo.get_id()).toUri();
        return ResponseEntity.created(location).build();
    }

    /*
    ENDPOINT para actualizar un período
    Se debe mandar el id por la URL y los nuevos datos en formato JSON del período que se quiere actualizar
    Ej:
    http://localhost:8080/sgcnegocio/periodos/61133f919761987103770a49
    JSON
    {
        "producto": "Producto/Servicio A",
        "pmti": "3 días",
        "tiempoIdealRecuperacion": "2 semanas",
        "usuario": "nombre"
    }
    */

    @PutMapping("/{id}")
    public ResponseEntity<Object> modificar(@RequestBody PeriodoTolerable periodo,
                                            @PathVariable(value = "id") String id) {
        periodo.set_id(id);
        service.actualizar(periodo);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    /*
    ENDPOINT
    Se debe mandar unicamente el id en el URL cuando se va a eliminar un período
    Ej:
    http://localhost:8080/sgcnegocio/periodos/61133f919761987103770a49
    */

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminarPorId(@PathVariable("id") String id) {
        service.eliminarDocumento(id);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
