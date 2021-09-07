package edu.uce.seguridad.controller;

import edu.uce.seguridad.model.BIAAutomatizacionDeControlEnRecursos;
import edu.uce.seguridad.service.service.BIAAutomatizacionDeControlEnRecursosService;
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
@RequestMapping("/sgcnegocio/bIAAutomatizacionDeControlEnRecursos")
@AllArgsConstructor
public class BIAAutomatizacionDeControlEnRecursosController {

    private BIAAutomatizacionDeControlEnRecursosService bIAAutomatizacionDeControlEnRecursosService;

    @GetMapping("/buscarBIAAutomatizacionDeControlEnRecursosPorUser/{user}")
    public ResponseEntity<?> buscarBIAAutomatizacionDeControlEnRecursosPorUser(@PathVariable(value = "user") String user) {
        List<BIAAutomatizacionDeControlEnRecursos> contacto = this.bIAAutomatizacionDeControlEnRecursosService.buscarPorUserFiltrarPorInmueble(user);
        return new ResponseEntity<>(contacto, HttpStatus.OK);
    }

    /*
    ejemplo:
    {
        "user":"vsaavedrae12",
        "inmueble":"Manual",
        "equipos":"Automatizada",
        "tecnologico":"Automatizada",
         "humanos":"Automatizada",
         "primordiales":"Automatizada",
         "secundarios":"Semi-Automatizada"
    }
    */
    @PostMapping("/agregarBIAAutomatizacionDeControlEnRecursos")
    public ResponseEntity<?> agregarBIAAutomatizacionDeControlEnRecursos(@RequestBody BIAAutomatizacionDeControlEnRecursos contacto) {
        BIAAutomatizacionDeControlEnRecursos contactoNew = this.bIAAutomatizacionDeControlEnRecursosService.agregar(contacto);
        return new ResponseEntity<>(contactoNew, HttpStatus.CREATED);
    }

    @PutMapping("/actualizarBIAAutomatizacionDeControlEnRecursos/{id}")
    public ResponseEntity<?> actualizarBIAAutomatizacionDeControlEnRecursos(@RequestBody BIAAutomatizacionDeControlEnRecursos contacto, @PathVariable(value = "id") String id) {
        contacto.set_id(id);
        BIAAutomatizacionDeControlEnRecursos contactoActualizado = this.bIAAutomatizacionDeControlEnRecursosService.actualizar(contacto);
        return new ResponseEntity<>(contactoActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/eliminarBIAAutomatizacionDeControlEnRecursos/{id}")
    public ResponseEntity<?> eliminarBIAAutomatizacionDeControlEnRecursos(@PathVariable(value = "id") String id) {
        this.bIAAutomatizacionDeControlEnRecursosService.eliminarDocumento(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
