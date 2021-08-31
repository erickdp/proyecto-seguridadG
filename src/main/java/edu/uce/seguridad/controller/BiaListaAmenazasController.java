package edu.uce.seguridad.controller;

import edu.uce.seguridad.model.BiaListaAmenazas;
import edu.uce.seguridad.model.BiaValoracionGeneral;
import edu.uce.seguridad.service.service.BiaListaAmenazasService;
import edu.uce.seguridad.service.service.BiaValoracionGeneralService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static edu.uce.seguridad.util.Utileria.agregarAmenazasGeneral;

@RestController
@RequestMapping("/sgcnegocio/biaListaAmanaza")
@AllArgsConstructor
public class BiaListaAmenazasController {

    private BiaListaAmenazasService listaAmenazasService;

    private BiaValoracionGeneralService valoracionGeneralService;

    @GetMapping("/{user}")
    public ResponseEntity<?> buscarPorUser(@PathVariable(value = "user") String user) {
        BiaListaAmenazas lista = this.listaAmenazasService.buscarPorUsuario(user);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> agregar(@RequestBody BiaListaAmenazas data) {
        BiaListaAmenazas amenazas = this.listaAmenazasService.agregar(data);
        // Lógica para crear el siguiente 6.4 BiaValoracionGeneral

        this.valoracionGeneralService.agregar(BiaValoracionGeneral.builder()
                .usuario(data.getUsuario())
                .amenazas(agregarAmenazasGeneral(amenazas))
                .build());

        return new ResponseEntity<>(amenazas, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar (@RequestBody BiaListaAmenazas amenaza, @PathVariable(value = "id") String id) {
        amenaza.set_id(id);
        BiaListaAmenazas biaListaAmenazas = this.listaAmenazasService.actualizar(amenaza);
        // Lógica para Actualizar el siguiente 6.4 BiaValoracionGeneral

        BiaValoracionGeneral biaValoracionGene = this.valoracionGeneralService.buscarPorUsuario(amenaza.getUsuario());
        this.valoracionGeneralService.actualizar(BiaValoracionGeneral.builder()
                ._id(biaValoracionGene.get_id())
                .usuario(amenaza.getUsuario())
                .amenazas(agregarAmenazasGeneral(biaListaAmenazas))
                .build());


        return new ResponseEntity<>(amenaza, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable(value = "id") String id) {
        this.listaAmenazasService.eliminarDocumento(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
