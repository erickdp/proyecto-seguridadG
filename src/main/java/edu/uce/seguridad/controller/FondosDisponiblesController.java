package edu.uce.seguridad.controller;

import edu.uce.seguridad.model.FondosDisponibles;
import edu.uce.seguridad.service.service.FondosDisponiblesService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/sgcnegocio/fondosDisponibles")
@AllArgsConstructor
public class FondosDisponiblesController {

    private FondosDisponiblesService fondosDisponiblesService;

    @GetMapping
    public ResponseEntity<?> getAllFormularios() {
        List<FondosDisponibles> list = this.fondosDisponiblesService.buscarTodos();
        return new ResponseEntity<>(list, OK);
    }

    @PostMapping
    public ResponseEntity<?> saveFormulario(@RequestBody FondosDisponibles fondosDisponibles) {
        FondosDisponibles fondosDisponiblesN = this.fondosDisponiblesService.agregar(fondosDisponibles);
        return new ResponseEntity<>(fondosDisponiblesN, ACCEPTED);
    }

    @PutMapping
    public ResponseEntity<?> updateFormulario(@RequestBody FondosDisponibles fondosDisponibles) {
        FondosDisponibles fondosDisponiblesA = this.fondosDisponiblesService.actualizar(fondosDisponibles);
        return new ResponseEntity<>(fondosDisponiblesA, CREATED);
    }

    @GetMapping("/{usuario}")
    public ResponseEntity<?> getFormularioByUsername(@PathVariable("usuario") String usuario) {
        FondosDisponibles fondosDisponibles = this.fondosDisponiblesService.buscaPorId(usuario);
        return new ResponseEntity<>(fondosDisponibles, OK);
    }
}
