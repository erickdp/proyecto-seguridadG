package edu.uce.seguridad.controller;

import edu.uce.seguridad.model.BIAPregunta;
import edu.uce.seguridad.service.service.BIAPreguntaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;

// By Erick
@RestController
@RequestMapping("/sgcnegocio/preguntasbia")
@AllArgsConstructor
public class BIAPreguntaController {

    private BIAPreguntaService biaPreguntaService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(this.biaPreguntaService.buscarTodos());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody BIAPregunta pregunta) {
        return new ResponseEntity<>(this.biaPreguntaService.agregar(pregunta), CREATED);
    }

    @PutMapping("/{posicion}") // La posicion sirve para determinar la respuesta en la pregunta de los usuarios que hayan asignado una calificacion o para eliminarlo de la lista de respuestas
    public ResponseEntity<?> update(
            @RequestBody BIAPregunta pregunta,
            @PathVariable Integer posicion) {
        return new ResponseEntity<>(this.biaPreguntaService.actualizarPreguntaPosicion(pregunta, posicion), ACCEPTED);
    }

    @DeleteMapping("/{id}/{posicion}")
    @ResponseStatus(ACCEPTED)
    public void delete(
            @PathVariable("id") String identificador,
            @PathVariable("posicion") Integer posicion) {
        this.biaPreguntaService.eliminarPreguntaPosicion(identificador, posicion);
    }

}
