package edu.uce.seguridad.controller;

import edu.uce.seguridad.model.FormularioPlanDePrueba;
import edu.uce.seguridad.service.service.FormularioPlanDePruebaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sgcnegocio/formularioPlanDePrueba")
@AllArgsConstructor

public class FormularioPlanDePruebaController {

    private FormularioPlanDePruebaService formularioPlanDePruebaService;

    @GetMapping("/buscarPlanDePruebaPorUser/{user}")
    public ResponseEntity<?> buscarPlanDePruebaPorUser(@PathVariable(value = "user") String user) {
        List<FormularioPlanDePrueba> contacto = this.formularioPlanDePruebaService.buscarPorUserFiltrarPorTipoDeEjercicio(user);
        return new ResponseEntity<>(contacto, HttpStatus.OK);
    }

    @PostMapping("/agregarPlanDePrueba")
    public ResponseEntity<?> agregarPlanDePrueba(@RequestBody FormularioPlanDePrueba contacto) {
        FormularioPlanDePrueba contactoNew = this.formularioPlanDePruebaService.agregar(contacto);
        return new ResponseEntity<>(contactoNew, HttpStatus.CREATED);
    }

    @PutMapping("/actualizarPlanDePrueba/{id}")
    public ResponseEntity<?> actualizarPlanDePrueba(@RequestBody FormularioPlanDePrueba contacto, @PathVariable(value = "id") String id) {
        contacto.set_id(id);
        FormularioPlanDePrueba contactoActualizado = this.formularioPlanDePruebaService.actualizar(contacto);
        return new ResponseEntity<>(contactoActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/eliminarPlanDePrueba/{id}")
    public ResponseEntity<?> eliminarPlanDePrueba(@PathVariable(value = "id") String id) {
        this.formularioPlanDePruebaService.eliminarDocumento(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
