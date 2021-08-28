package edu.uce.seguridad.controller;

import edu.uce.seguridad.model.Bia2;
import edu.uce.seguridad.service.service.Bia2Service;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/sgcnegocio/bia2")
public class Bia2Controller {

    private Bia2Service bia2Service;

    @PostMapping("/agregarBia2")
    public ResponseEntity<?>agregarBia2(@RequestBody Bia2 biA){
        Bia2 bia21 = this.bia2Service.agregar(biA);
        return new ResponseEntity<>(biA, HttpStatus.CREATED);
    }

    @PutMapping("/actualizarbia2/{id}")
    public ResponseEntity<?>actualizarBia2(@RequestBody Bia2 bi,@PathVariable("id")String id){
        bi.set_id(id);
        this.bia2Service.actualizar(bi);
        return new ResponseEntity<>(bi, HttpStatus.OK);
    }

    @DeleteMapping("/eliminarBia2/{id}")
    public ResponseEntity<?> eliminarBia2(@PathVariable("id") String id){
        this.bia2Service.eliminarDocumento(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/buscarBia2/{user}")
    public ResponseEntity<?> buscarBia2(@PathVariable("user") String user){
        List<Bia2> listabia = this.bia2Service.buscarporUsuario(user);
        return new ResponseEntity<>(listabia, HttpStatus.OK);
    }

}
