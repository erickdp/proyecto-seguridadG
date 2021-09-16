package edu.uce.seguridad.controller;

import edu.uce.seguridad.model.BIAAnalisiImpactoNegocio;
import edu.uce.seguridad.service.service.BIAAnalisiImpactoNegocioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sgcnegocio/BIAAnalisisImpacto")
@AllArgsConstructor
public class BIAAnalisiImpactoNegocioController {

    private BIAAnalisiImpactoNegocioService impactoNegocioService;

    @GetMapping("/{nombreUsuario}")
    public ResponseEntity<?> getFormByUser(@PathVariable("nombreUsuario") String nombreUsuario) {
        return new ResponseEntity<>(this.impactoNegocioService.buscaPorId(nombreUsuario), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody BIAAnalisiImpactoNegocio bia6) {
        return new ResponseEntity<>(this.impactoNegocioService.agregar(bia6), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody BIAAnalisiImpactoNegocio bia6) {
        return new ResponseEntity<>(this.impactoNegocioService.agregar(bia6), HttpStatus.ACCEPTED);
    }

}
