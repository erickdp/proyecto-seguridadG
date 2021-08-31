package edu.uce.seguridad.controller;

import edu.uce.seguridad.model.BiaListaAmenazas;
import edu.uce.seguridad.service.service.BiaListaAmenazasService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/sgcnegocio/biaListaAmanaza")
@AllArgsConstructor
public class BiaListaAmenazasController {

    private BiaListaAmenazasService listaAmenazasService;

    @GetMapping("/{user}")
    public ResponseEntity<?> buscarPorUser(@PathVariable(value = "user") String user) {
        BiaListaAmenazas lista = this.listaAmenazasService.buscarPorUsuario(user);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> agregarRIP(@RequestBody BiaListaAmenazas data) {
        BiaListaAmenazas amenazas = this.listaAmenazasService.agregar(data);
        return new ResponseEntity<>(amenazas, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarRIP (@RequestBody BiaListaAmenazas amenaza, @PathVariable(value = "id") String id) {
        amenaza.set_id(id);
        BiaListaAmenazas biaListaAmenazas = this.listaAmenazasService.actualizar(amenaza);
        return new ResponseEntity<>(amenaza, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarRIP(@PathVariable(value = "id") String id) {
        this.listaAmenazasService.eliminarDocumento(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
