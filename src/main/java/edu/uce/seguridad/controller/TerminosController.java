package edu.uce.seguridad.controller;

import edu.uce.seguridad.model.Termino;
import edu.uce.seguridad.service.service.TerminoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/sgcnegocio/terminos")
@AllArgsConstructor
public class TerminosController {

    private TerminoService terminoService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(this.terminoService.buscarTodos());
    }

    @GetMapping("/{termino}")
    public ResponseEntity<?> getByName(
            @PathVariable String termino
    ) {
        return ResponseEntity.ok(this.terminoService.buscaPorId(termino));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Termino nuevoTermino) {
        return new ResponseEntity<>(this.terminoService.agregar(nuevoTermino), CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Termino nuevoTermino) {
        return new ResponseEntity<>(this.terminoService.actualizar(nuevoTermino), ACCEPTED);
    }

    @DeleteMapping("/{termino}")
    @ResponseStatus(ACCEPTED)
    public void delete(@PathVariable String termino) {
        this.terminoService.eliminarDocumento(termino);
    }


}
