package edu.uce.seguridad.controller;

import edu.uce.seguridad.model.BIAPeriocidadDeAccionesDeControlEnRecursos;
import edu.uce.seguridad.service.service.BIAPeriocidadDeAccionesDeControlEnRecursosService;
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
@RequestMapping("/sgcnegocio/bIAPeriocidadDeAccionesDeControlEnRecursos")
@AllArgsConstructor
public class BIAPeriocidadDeAccionesDeControlEnRecursosController {
    private BIAPeriocidadDeAccionesDeControlEnRecursosService bIAPeriocidadDeAccionesDeControlEnRecursosService;
    
    @GetMapping("/buscarBIAPeriocidadDeAccionesDeControlEnRecursosPorUser/{user}")
    public ResponseEntity<?> buscarBIAPeriocidadDeAccionesDeControlEnRecursosPorUser(@PathVariable(value = "user") String user) {
        List<BIAPeriocidadDeAccionesDeControlEnRecursos> contacto = this.bIAPeriocidadDeAccionesDeControlEnRecursosService.buscarPorUserFiltrarPorInmueble(user);
        return new ResponseEntity<>(contacto, HttpStatus.OK);
    }

    /*
    ejemplo:
    {
        "user":"vsaavedrae12",
        "inmueble":"Permanente",
        "equipos":"Ocacional",
        "tecnologico":"Ocacional",
         "humanos":"Permanente",
         "primordiales":"Permanente",
         "secundarios":"Ocacional"
    }
    */
    
    @PostMapping("/agregarBIAPeriocidadDeAccionesDeControlEnRecursos")
    public ResponseEntity<?> agregarBIAPeriocidadDeAccionesDeControlEnRecursos(@RequestBody BIAPeriocidadDeAccionesDeControlEnRecursos contacto) {
        BIAPeriocidadDeAccionesDeControlEnRecursos contactoNew = this.bIAPeriocidadDeAccionesDeControlEnRecursosService.agregar(contacto);      
        return new ResponseEntity<>(contactoNew, HttpStatus.CREATED);
    }
    
        @PutMapping("/actualizarBIAPeriocidadDeAccionesDeControlEnRecursos/{id}")
    public ResponseEntity<?> actualizarBIAPeriocidadDeAccionesDeControlEnRecursos(@RequestBody BIAPeriocidadDeAccionesDeControlEnRecursos contacto, @PathVariable(value = "id") String id) {
        contacto.set_id(id);
        BIAPeriocidadDeAccionesDeControlEnRecursos contactoActualizado = this.bIAPeriocidadDeAccionesDeControlEnRecursosService.actualizar(contacto);
        return new ResponseEntity<>(contactoActualizado, HttpStatus.OK);
    }
    
        @DeleteMapping("/eliminarBIAPeriocidadDeAccionesDeControlEnRecursos/{id}")
    public ResponseEntity<?> eliminarBIAPeriocidadDeAccionesDeControlEnRecursos(@PathVariable(value = "id") String id) {
        this.bIAPeriocidadDeAccionesDeControlEnRecursosService.eliminarDocumento(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
