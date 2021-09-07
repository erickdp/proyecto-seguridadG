package edu.uce.seguridad.controller;

import edu.uce.seguridad.model.BIAEficenciaBasadaEnControlesDeRecursos;
import edu.uce.seguridad.service.service.BIAEficenciaBasadaEnControlesDeRecursosService;
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
@RequestMapping("/sgcnegocio/bIAEficenciaBasadaEnControlesDeRecursos")
@AllArgsConstructor
public class BIAEficenciaBasadaEnControlesDeRecursosController {
    private BIAEficenciaBasadaEnControlesDeRecursosService bIAEficenciaBasadaEnControlesDeRecursosService;

    @GetMapping("/buscarBIAEficenciaBasadaEnControlesDeRecursosPorUser/{user}")
    public ResponseEntity<?> buscarBIAEficenciaBasadaEnControlesDeRecursosPorUser(@PathVariable(value = "user") String user) {
        List<BIAEficenciaBasadaEnControlesDeRecursos> contacto = this.bIAEficenciaBasadaEnControlesDeRecursosService.buscarPorUserFiltrarPorInmueble(user);
        return new ResponseEntity<>(contacto, HttpStatus.OK);
    }

    /*
    ejemplo: 
    {
        "user":"vsaavedrae12",
        "inmueble":"Media",
        "equipos":"Alta",
        "tecnologico":"Media",
         "humanos":"Alta",
         "primordiales":"Muy Alta",
         "secundarios":"Media"
    }
    */
    @PostMapping("/agregarBIAEficenciaBasadaEnControlesDeRecursos")
    public ResponseEntity<?> agregarBIAEficenciaBasadaEnControlesDeRecursos(@RequestBody BIAEficenciaBasadaEnControlesDeRecursos contacto) {
        BIAEficenciaBasadaEnControlesDeRecursos contactoNew = this.bIAEficenciaBasadaEnControlesDeRecursosService.agregar(contacto);
        return new ResponseEntity<>(contactoNew, HttpStatus.CREATED);
    }

    @PutMapping("/actualizarBIAEficenciaBasadaEnControlesDeRecursos/{id}")
    public ResponseEntity<?> actualizarBIAEficenciaBasadaEnControlesDeRecursos(@RequestBody BIAEficenciaBasadaEnControlesDeRecursos contacto, @PathVariable(value = "id") String id) {
        contacto.set_id(id);
        BIAEficenciaBasadaEnControlesDeRecursos contactoActualizado = this.bIAEficenciaBasadaEnControlesDeRecursosService.actualizar(contacto);
        return new ResponseEntity<>(contactoActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/eliminarBIAEficenciaBasadaEnControlesDeRecursos/{id}")
    public ResponseEntity<?> eliminarBIAEficenciaBasadaEnControlesDeRecursos(@PathVariable(value = "id") String id) {
        this.bIAEficenciaBasadaEnControlesDeRecursosService.eliminarDocumento(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
