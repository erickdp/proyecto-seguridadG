package edu.uce.seguridad.controller;

import edu.uce.seguridad.model.BIACuestionario;
import edu.uce.seguridad.service.service.BIACuestionarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sgcnegocio/BIACuestionario")
@AllArgsConstructor
public class BIACuestionarioController {

    private BIACuestionarioService biaCuestionarioService;

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody BIACuestionario biaCuestionario) {
        return new ResponseEntity<>(this.biaCuestionarioService.agregar(biaCuestionario), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> actualizar(@RequestBody BIACuestionario biaCuestionario) {
        return new ResponseEntity<>(this.biaCuestionarioService.actualizar(biaCuestionario), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{nombreUsuario}")
    public ResponseEntity<?> findByUsername(@PathVariable("nombreUsuario") String user) {
        return new ResponseEntity<>(this.biaCuestionarioService.buscaPorId(user), HttpStatus.OK);
    }

    @GetMapping("promedio/{organizacion}")
    public ResponseEntity<?> obtenerPromedioCuestionario(@PathVariable String organizacion) {
        return new ResponseEntity<>(this.biaCuestionarioService.obtenerPromedioCuestionario(organizacion), HttpStatus.OK);
    }

    @GetMapping("/promedioTotal")
    public ResponseEntity<?> obtenerPromedioCuestionarioTotal() {
        return new ResponseEntity<>(this.biaCuestionarioService.obtenerPromedioCuestionario(null), HttpStatus.OK);
    }
}
