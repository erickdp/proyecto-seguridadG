package edu.uce.seguridad.controller;

import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.ActividadesPrioritarias;
import edu.uce.seguridad.service.service.ActividadesPrioritariasService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/sgcnegocio/actividades")
@AllArgsConstructor
public class ActividadesPrioritariasController {
    private ActividadesPrioritariasService service;

    /*
    ENDPOINT que busca todas todas las actividades prioritarias
    Ej:
    http://localhost:8080/sgcnegocio/actividades/60fcb37db3c0630156388256
    * */

    @GetMapping
    public ResponseEntity<List<ActividadesPrioritarias>> listar() {
        List<ActividadesPrioritarias> actividades = service.buscarTodos();
        return new ResponseEntity<List<ActividadesPrioritarias>>(actividades, HttpStatus.OK);
    }

    /*
    ENDPOINT que busca todas las actividades prioritarias por un identificador, el id se debe enviar por la URL
    Ej:
    http://localhost:8080/sgcnegocio/actividades/buscarActividadPorId/60fcb37db3c0630156388256
    * */

    @GetMapping("/buscarActividadPorId/{id}")
    public ResponseEntity<ActividadesPrioritarias> listarPorId(@PathVariable("id") String idActividad) {
        ActividadesPrioritarias actividad = service.buscaPorId(idActividad);
        if (actividad == null) {
            throw new NoEncontradoExcepcion("respuesta", "Id no encontrado " + idActividad);
        }
        return new ResponseEntity<ActividadesPrioritarias>(actividad, HttpStatus.OK);
    }

    /*
    ENDPOINT que busca todas las actividades prioritarias por usuario, el nombre de usuario se debe enviar por la URL
    Ej:
    http://localhost:8080/sgcnegocio/actividades/buscarActividadesPorUsuario/nombre
    * */

    @GetMapping("/buscarActividadesPorUsuario/{usuario}")
    public ResponseEntity<List<ActividadesPrioritarias>> buscarPorUsuario(@PathVariable("usuario") String nombreUsuario) {
        List<ActividadesPrioritarias> actividades = service.buscarActividadesPorUsuario(nombreUsuario);
        return new ResponseEntity<List<ActividadesPrioritarias>>(actividades, HttpStatus.OK);
    }

    /*
    ENDPOINT para guardar una actividad prioritaria
    Se debe enviar un JSON con la actividad y los tiempos de recuperación junto con el usuario
    Ej:
    {
        "user": "60fcb37db3c0630156388256",
        "actividades": "La actividad prioritaria",
        "tiempoRecuperacion": "2 semanas"
    }
    */

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<Object> guardar(@RequestBody ActividadesPrioritarias actividadEnviada) {
        ActividadesPrioritarias actividad = service.agregar(actividadEnviada);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}")
                .buildAndExpand(actividad.get_id()).toUri();
        return ResponseEntity.created(location).build();
    }

    /*
    ENDPOINT para actualizar una actividad prioritaria
    Se debe mandar el id por la URL y los nuevos datos en formato JSON de la actividad que se quiere actualizar
    Ej:
    http://localhost:8080/sgcnegocio/actividades/60fdc3969845fe5c999df4d3
    {
        "user": "60fcb37db3c0630156388256",
        "actividades": "La actividad prioritaria",
        "tiempoRecuperacion": "2 semanas"
    }
    */

    @PutMapping("/{id}")
    public ResponseEntity<Object> modificar(@RequestBody ActividadesPrioritarias actividad, @PathVariable(value = "id") String id) {
        actividad.set_id(id);
        ActividadesPrioritarias actividadesPrioritariasUpdated = service.actualizar(actividad);
        return new ResponseEntity<Object>(actividadesPrioritariasUpdated, HttpStatus.OK);
    }

    /*
    ENDPOINT activo
    Se debe mandar unicamente el id en el URL para eliminar una actividad
    Ej:
    http://localhost:8080/sgcnegocio/actividades/60fdc3969845fe5c999df4d3
    */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminarPorId(@PathVariable("id") String id) {
        service.eliminarDocumento(id);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
