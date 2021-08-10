package edu.uce.seguridad.controller;

import edu.uce.seguridad.model.FormularioComparativoImpacto;
import edu.uce.seguridad.service.service.FormularioImpactoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sgcnegocio/formularioImpactoComparativo" )
@AllArgsConstructor
public class FormularioControllerImpactoComparativo {

    private FormularioImpactoService formularioImpactoService;


    @PostMapping("/agregarImpacto")
    public ResponseEntity<?> agregarImpacto(@RequestBody FormularioComparativoImpacto impacto){
        FormularioComparativoImpacto impactoNew = this.formularioImpactoService.agregar(impacto);
        return new ResponseEntity<>(impactoNew, HttpStatus.CREATED);
    }

    @PutMapping("/actualizarImpacto/{id}")
    public ResponseEntity<?> actualizarImpacto(@RequestBody FormularioComparativoImpacto impacto, @PathVariable(value = "id")String id){
        impacto.setId(id);
        FormularioComparativoImpacto impactoActualizado= this.formularioImpactoService.actualizar(impacto);
        return new ResponseEntity<>(impactoActualizado, HttpStatus.OK);
    }
    @DeleteMapping("eliminarImpacto/{id}")
    public ResponseEntity<?> eliminarImpacto(@PathVariable(value = "id")String id){
        this.formularioImpactoService.eliminarDocumento(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping({"/buscarFormularioImpactoPorUser/{usuario}"})
    public ResponseEntity<?>buscaImpactoUsuario(@PathVariable(value = "usuario")String usuario){
        FormularioComparativoImpacto impactoUsuario = this.formularioImpactoService.buscarFormularioImpPorUsua(usuario);
        return  new ResponseEntity<>(impactoUsuario, HttpStatus.OK);
    }


}


