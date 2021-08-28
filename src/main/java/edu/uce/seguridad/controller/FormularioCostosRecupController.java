package edu.uce.seguridad.controller;

import edu.uce.seguridad.model.FormularioCostosRecup;
import edu.uce.seguridad.service.service.FormularioCostosRecupService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sgcnegocio/costos")
@AllArgsConstructor
public class FormularioCostosRecupController {

    private FormularioCostosRecupService formularioCostosRecupService;

    @GetMapping("/buscarPorUser/{user}")
    public ResponseEntity<?> buscarPorUser(@PathVariable("user") String user) {
        FormularioCostosRecup costos = this.formularioCostosRecupService.buscarPorUsuario(user);
        return new ResponseEntity<>(costos, HttpStatus.OK);
    }

//    @PostMapping("/agregarCosto")
//    public ResponseEntity<?> agregar(@RequestBody FormularioCostosRecup fomrCost) {
//        FormularioCostosRecup cost = this.formularioCostosRecupService.agregar(fomrCost);
//        return new ResponseEntity<>(cost, HttpStatus.CREATED);
//    }

    @PutMapping("/actualizarCosto/{id}")
    public ResponseEntity<?> actualizar(@RequestBody FormularioCostosRecup fomrCost, @PathVariable("id") String id) {
        fomrCost.set_id(id);
        this.formularioCostosRecupService.actualizar(fomrCost);
        return new ResponseEntity<>(fomrCost, HttpStatus.CREATED);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable("id") String id) {
        this.formularioCostosRecupService.eliminarDocumento(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
