package edu.uce.seguridad.controller;

import edu.uce.seguridad.model.EvacuacionYRescate;
import edu.uce.seguridad.service.service.EvacuacionYRescateService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/sgcnegocio/evacuacionyrescate")
@AllArgsConstructor
public class EvacuacionYRescateController {

    @Autowired
    private EvacuacionYRescateService evacuacionYRescateService;

    @GetMapping
    public ResponseEntity<?> getAllFormularios() {
        List<EvacuacionYRescate> evacuacionYRescates = this.evacuacionYRescateService.buscarTodos();
        return new ResponseEntity<List<EvacuacionYRescate>>(evacuacionYRescates, OK);
    }

    @GetMapping("/{nombreUsuario}")
    public ResponseEntity<?> getFormulariosByUser(@PathVariable String nombreUsuario) {
        Collection<EvacuacionYRescate> evacuacionYRescates = this.evacuacionYRescateService.buscarPorUsuario(nombreUsuario);
        return new ResponseEntity<Collection<EvacuacionYRescate>>(evacuacionYRescates, OK);
    }

    @GetMapping("/{nombreUsuario}/{departamento}")
    public ResponseEntity<?> getFormulariosByUserAndDepartamento(
            @PathVariable String nombreUsuario,
            @PathVariable String departamento) {
        EvacuacionYRescate evacuacionYRescates = this.evacuacionYRescateService.buscarPorUsuarioYDepartamento(nombreUsuario, departamento);
        return new ResponseEntity<EvacuacionYRescate>(evacuacionYRescates, OK);
    }


    @PutMapping
    public ResponseEntity<?> updateFormulario(@RequestBody EvacuacionYRescate evacuacionYRescate) {
        EvacuacionYRescate evacuacionYRescates = this.evacuacionYRescateService.actualizar(evacuacionYRescate);
        return new ResponseEntity<EvacuacionYRescate>(evacuacionYRescates, OK);
    }

    @PostMapping
    public ResponseEntity<?> saveFormulario(@RequestBody EvacuacionYRescate evacuacionYRescate) {
        EvacuacionYRescate evacuacionYRescates = this.evacuacionYRescateService.agregar(evacuacionYRescate);
        return new ResponseEntity<EvacuacionYRescate>(evacuacionYRescates, OK);
    }
}
