package edu.uce.seguridad.controller;

import edu.uce.seguridad.model.HojaDeRevisionDeGerencia;
import edu.uce.seguridad.service.service.HojaDeRevisionDeGerenciaService;
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
@RequestMapping("/sgcnegocio/hojaDeRevisionDeGerencia")
@AllArgsConstructor
public class HojaDeRevisionDeGerenciaController {
    
    private HojaDeRevisionDeGerenciaService hojaDeRevisionDeGerenciaService;
    
        @GetMapping("/buscarHojaDeRevisionDeGerenciaPorUser/{user}")
    public ResponseEntity<?> buscarHojaDeRevisionDeGerenciaPorUser(@PathVariable(value = "user") String user) {
        List<HojaDeRevisionDeGerencia> contacto = this.hojaDeRevisionDeGerenciaService.buscarPorUserFiltrarPorAsuntoARevisaryVerificar(user);
        return new ResponseEntity<>(contacto, HttpStatus.OK);
    }

    @PostMapping("/agregarHojaDeRevisionDeGerencia")
    public ResponseEntity<?> agregarHojaDeRevisionDeGerencia(@RequestBody HojaDeRevisionDeGerencia contacto) {
        HojaDeRevisionDeGerencia contactoNew = this.hojaDeRevisionDeGerenciaService.agregar(contacto);
        return new ResponseEntity<>(contactoNew, HttpStatus.CREATED);
    }

    @PutMapping("/actualizarHojaDeRevisionDeGerencia/{id}")
    public ResponseEntity<?> actualizarHojaDeRevisionDeGerencia(@RequestBody HojaDeRevisionDeGerencia contacto, @PathVariable(value = "id") String id) {
        contacto.set_id(id);
        HojaDeRevisionDeGerencia contactoActualizado = this.hojaDeRevisionDeGerenciaService.actualizar(contacto);
        return new ResponseEntity<>(contactoActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/eliminarHojaDeRevisionDeGerencia/{id}")
    public ResponseEntity<?> eliminarHojaDeRevisionDeGerencia(@PathVariable(value = "id") String id) {
        this.hojaDeRevisionDeGerenciaService.eliminarDocumento(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    
    
}
