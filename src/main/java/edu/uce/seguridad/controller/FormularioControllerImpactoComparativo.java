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

    /*
        ENDPOINT activo
        Se debe enviar un JSON con user, Departamento que maneja cada producto/servicio ,
        Los valores de impacto interno y externo seran por defecto G:M:CH (Grande, Mediano, Chico)
        impacto interno G:M:Ch y el impacto externo G:M:Ch
        ,probabilidad, prioridad
        Ej:
            http://localhost:8080/sgcnegocio/formularioImpactoComparativo/agregarImpacto
        {
             "user": "60fcb37db3c0630156388256",
             "departamentoProducto": "Departamento economico",
             "impactoExterno": "CH",
             "impactoInterno": "M"
        }
        */


    @PostMapping("/agregarImpacto")
    public ResponseEntity<?> agregarImpacto(@RequestBody FormularioComparativoImpacto impacto){
        FormularioComparativoImpacto impactoNew = this.formularioImpactoService.agregar(impacto);
        return new ResponseEntity<>(impactoNew, HttpStatus.CREATED);
    }

    /*
        ENDPOINT activo
         Se debe mandar el id del Formulario por la URL y los nuevos datos en formato JSON
        que se quiere actualizar.
        Se debe enviar  user, Departamento que maneja cada producto/servicio ,
        Los valores de impacto interno y externo seran por defecto G:M:CH (Grande, Mediano, Chico)
        impacto interno G:M:Ch y el impacto externo G:M:Ch
        ,probabilidad, prioridad
        Ej:
            http://localhost:8080/sgcnegocio/formularioImpactoComparativo/actualizarImpacto/6111d4e653d63b5e7926dff6
        {
             "user": "60fcb37db3c0630156388256",
             "departamentoProducto": "Departamento economico",
             "impactoExterno": "CH",
             "impactoInterno": "M"
        }
        */

    @PutMapping("/actualizarImpacto/{id}")
    public ResponseEntity<?> actualizarImpacto(@RequestBody FormularioComparativoImpacto impacto, @PathVariable(value = "id")String id){
        impacto.setId(id);
        FormularioComparativoImpacto impactoActualizado= this.formularioImpactoService.actualizar(impacto);
        return new ResponseEntity<>(impactoActualizado, HttpStatus.OK);
    }

     /*
        ENDPOINT activo
         Se debe mandar el id del Formulario por la URL para poder eliminar el registro

        Ej:
            http://localhost:8080/sgcnegocio/formularioImpactoComparativo/eliminarImpacto/6111d4e653d63b5e7926dff6
        {

        }
        */


    @DeleteMapping("eliminarImpacto/{id}")
    public ResponseEntity<?> eliminarImpacto(@PathVariable(value = "id")String id){
        this.formularioImpactoService.eliminarDocumento(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
     /*
        ENDPOINT activo
         Se debe mandar el user del Formulario por la URL para poder buscar el registro

        Ej:
            http://localhost:8080/sgcnegocio/formularioImpactoComparativo/buscarFormularioImpactoPorUser/60fcb37db3c0630156388256
        {

        }
        */

    @GetMapping({"/buscarFormularioImpactoPorUser/{usuario}"})
    public ResponseEntity<?>buscaImpactoUsuario(@PathVariable(value = "usuario")String usuario){
        FormularioComparativoImpacto impactoUsuario = this.formularioImpactoService.buscarFormularioImpPorUsua(usuario);
        return  new ResponseEntity<>(impactoUsuario, HttpStatus.OK);
    }


}


