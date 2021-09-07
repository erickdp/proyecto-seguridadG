package edu.uce.seguridad.controller;

import edu.uce.seguridad.model.BIAControlesAlineadosALosImpactos;
import edu.uce.seguridad.service.service.BIAControlesAlineadosALosImpactosService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sgcnegocio/bIAControlesAlineadosALosImpactos")
@AllArgsConstructor
public class BIAControlesAlineadosALosImpactosController {
    private BIAControlesAlineadosALosImpactosService bIAControlesAlineadosALosImpactosService;
    
    @GetMapping("/buscarBIAControlesAlineadosALosImpactosPorUser/{user}")
    public ResponseEntity<?> buscarBIAControlesAlineadosALosImpactosPorUser(@PathVariable(value = "user") String user) {
        List<BIAControlesAlineadosALosImpactos> contacto = this.bIAControlesAlineadosALosImpactosService.buscarPorUserFiltrarPorInmueble(user);
        return new ResponseEntity<>(contacto, HttpStatus.OK);
    }
    /*
    Ejemplo:
    {
        "user":"vsaavedrae12",
        "inmueble":"Correctivo",
        "equipos":"Preventivo",
        "tecnologico":"Preventivo",
         "humanos":"Preventivo",
         "primordiales":"Correctivo",
         "secundarios":"Preventivo"
    }
    */

    @PostMapping("/agregarBIAControlesAlineadosALosImpactos")
    public ResponseEntity<?> agregarBIAControlesAlineadosALosImpactos(@RequestBody BIAControlesAlineadosALosImpactos contacto) {
        BIAControlesAlineadosALosImpactos contactoNew = this.bIAControlesAlineadosALosImpactosService.agregar(contacto);      
        return new ResponseEntity<>(contactoNew, HttpStatus.CREATED);
    }
    
        @PutMapping("/actualizarBIAControlesAlineadosALosImpactos/{id}")
    public ResponseEntity<?> actualizarBIAControlesAlineadosALosImpactos(@RequestBody BIAControlesAlineadosALosImpactos contacto, @PathVariable(value = "id") String id) {
        contacto.set_id(id);
        BIAControlesAlineadosALosImpactos contactoActualizado = this.bIAControlesAlineadosALosImpactosService.actualizar(contacto);
        return new ResponseEntity<>(contactoActualizado, HttpStatus.OK);
    }
    
        @DeleteMapping("/eliminarBIAControlesAlineadosALosImpactos/{id}")
    public ResponseEntity<?> eliminarBIAControlesAlineadosALosImpactos(@PathVariable(value = "id") String id) {
        this.bIAControlesAlineadosALosImpactosService.eliminarDocumento(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
