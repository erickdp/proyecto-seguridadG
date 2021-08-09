package edu.uce.seguridad.controller;

import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.ImpactoNegocio;
import edu.uce.seguridad.service.service.ImpactoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/sgcnegocio/impactos")
@AllArgsConstructor
public class ImpactoControlador {

    private ImpactoService service;

    @GetMapping
    public ResponseEntity<List<ImpactoNegocio>> listar() {
        List<ImpactoNegocio> impactos = service.buscarTodos();
        return new ResponseEntity<List<ImpactoNegocio>>(impactos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImpactoNegocio> listarPorId(@PathVariable("id") String idImpacto) {
        ImpactoNegocio impactoNegocio = service.buscaPorId(idImpacto);
        if (impactoNegocio == null) {
            throw new NoEncontradoExcepcion("respuesta", "Id no encontrado " + idImpacto);
        }
        return new ResponseEntity<ImpactoNegocio>(impactoNegocio, HttpStatus.OK);
    }

    @GetMapping("/{usuario}")
    public ResponseEntity<ImpactoNegocio> buscarPorUsuario(@PathVariable("usuario") String nombreUsuario) {
        ImpactoNegocio impactoNegocio = service.buscarImpactoPorUsuario(nombreUsuario);
        if (impactoNegocio == null) {
            throw new NoEncontradoExcepcion("respuesta", "Usuario no encontrado " + nombreUsuario);
        }
        return new ResponseEntity<ImpactoNegocio>(impactoNegocio, HttpStatus.OK);
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<Object> guardar(@RequestBody ImpactoNegocio impacto) {
        ImpactoNegocio impactoNegocio = service.agregar(impacto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}")
                .buildAndExpand(impactoNegocio.get_id()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Object> modificar(@RequestBody ImpactoNegocio impacto) {
        service.actualizar(impacto);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminar(@PathVariable("id") String id) {
        ImpactoNegocio impactoNegocio = service.buscaPorId(id);
        if (impactoNegocio == null) {
            throw new NoEncontradoExcepcion("Id no encontrado " + id);
        } else {
            service.eliminarDocumento(id);
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
