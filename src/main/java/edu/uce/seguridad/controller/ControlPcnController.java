package edu.uce.seguridad.controller;

import edu.uce.seguridad.model.ControlPcn;
import edu.uce.seguridad.service.service.ControlPcnService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/sgcnegocio/controlPcn")
@AllArgsConstructor
public class ControlPcnController {

    private ControlPcnService controlPcnService;

    @PostMapping
    private ResponseEntity<?> agregarCuestionario(@RequestBody ControlPcn controlPcn) {
        return new ResponseEntity<>(this.controlPcnService.agregar(controlPcn), CREATED);
    }

    @PutMapping
    private ResponseEntity<?> actualizarCuestionario(@RequestBody ControlPcn controlPcn) {
        return new ResponseEntity<>(this.controlPcnService.actualizar(controlPcn), ACCEPTED);
    }

    @GetMapping("/{nombreUsuario}")
    private ResponseEntity<?> getCuestionario(@PathVariable String nombreUsuario) {
        return new ResponseEntity<>(this.controlPcnService.buscaPorId(nombreUsuario), OK);
    }
}
