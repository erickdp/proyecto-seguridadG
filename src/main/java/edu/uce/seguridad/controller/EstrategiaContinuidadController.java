package edu.uce.seguridad.controller;

import edu.uce.seguridad.model.EstrategiasContinuidad;
import edu.uce.seguridad.service.service.EstrategiasContinuidadService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sgcnegocio/estrategiaContinuidad")
@AllArgsConstructor
public class EstrategiaContinuidadController {

    private EstrategiasContinuidadService estrategiasContinuidadService;

    @GetMapping
    public ResponseEntity<?> getEstrategias() {
        List<EstrategiasContinuidad> estrategiasContinuidad = this.estrategiasContinuidadService.buscarTodos();
        return new ResponseEntity<List<EstrategiasContinuidad>>(estrategiasContinuidad, HttpStatus.OK);
    }

    @GetMapping("/{usuario}")
    public ResponseEntity<?> getEstrategiaByUser(@PathVariable("usuario") String usuario) {
        List<EstrategiasContinuidad> estrategiasContinuidad = this.estrategiasContinuidadService.buscarPorUsuario(usuario);
        return new ResponseEntity<List<EstrategiasContinuidad>>(estrategiasContinuidad, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Void> agregarEstrategia(
            @RequestBody EstrategiasContinuidad estrategiasContinuidad
    ) {
        this.estrategiasContinuidadService.actualizar(estrategiasContinuidad);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
