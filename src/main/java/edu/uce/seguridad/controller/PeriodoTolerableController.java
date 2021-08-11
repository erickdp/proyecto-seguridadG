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

    @GetMapping
    public ResponseEntity<List<PeriodoTolerable>> listar() {
        List<PeriodoTolerable> periodo = service.buscarTodos();
        return new ResponseEntity<List<PeriodoTolerable>>(periodo, HttpStatus.OK);
    }

    @GetMapping("/buscarPeriodoPorId/{id}")
    public ResponseEntity<PeriodoTolerable> listarPorId(@PathVariable("id") String idPeriodo) {
        PeriodoTolerable periodo = service.buscaPorId(idPeriodo);
        if (periodo == null) {
            throw new NoEncontradoExcepcion("respuesta", "Id no encontrado " + idPeriodo);
        }
        return new ResponseEntity<PeriodoTolerable>(periodo, HttpStatus.OK);
    }

    @GetMapping("/buscarPeriodosPorUsuario/{usuario}")
    public ResponseEntity<List<PeriodoTolerable>> buscarPorUsuario(@PathVariable("usuario") String nombreUsuario) {
        List<PeriodoTolerable> periodos = service.buscarPeriodosPorUsuario(nombreUsuario);
        return new ResponseEntity<List<PeriodoTolerable>>(periodos, HttpStatus.OK);
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<Object> guardar(@RequestBody PeriodoTolerable periodoEnviado) {
        PeriodoTolerable periodo = service.agregar(periodoEnviado);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}")
                .buildAndExpand(periodo.get_id()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> modificar(@RequestBody PeriodoTolerable periodo,
                                            @PathVariable(value = "id") String id) {
        periodo.set_id(id);
        service.actualizar(periodo);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminarPorId(@PathVariable("id") String id) {
        service.eliminarDocumento(id);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
