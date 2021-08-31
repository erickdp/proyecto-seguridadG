package edu.uce.seguridad.controller;

import edu.uce.seguridad.model.BiaValoracionGeneral;
import edu.uce.seguridad.service.service.BiaValoracionGeneralService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/sgcnegocio/biaValoracionGeneral")
@AllArgsConstructor
public class BiaValoracionGeneralController {

    private BiaValoracionGeneralService biaValoracionGeneralService;

    @GetMapping("/{user}")
    public ResponseEntity<?> buscarRIPsPorUser(@PathVariable(value = "user") String user) {
        BiaValoracionGeneral biaValoracionGeneral = this.biaValoracionGeneralService.buscarPorUsuario(user);
        return new ResponseEntity<>(biaValoracionGeneral, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@RequestBody BiaValoracionGeneral vg, @PathVariable(value = "id") String id) {
        vg.set_id(id);
        BiaValoracionGeneral biaValoracionGeneral = this.biaValoracionGeneralService.actualizar(vg);
        return new ResponseEntity<>(biaValoracionGeneral, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable(value = "id") String id) {
        this.biaValoracionGeneralService.eliminarDocumento(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
