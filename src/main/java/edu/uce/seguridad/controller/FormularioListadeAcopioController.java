package edu.uce.seguridad.controller;

import edu.uce.seguridad.model.FormularioListadeAcopio;
import edu.uce.seguridad.service.service.FormularioListadeAcopioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/sgcnegocio/acopioPreparativos")
public class FormularioListadeAcopioController {

    private FormularioListadeAcopioService formularioListadeAcopioService;

    /*
        ENDPOINT activo
        Se debe enviar un JSON con user, categoria, dentro de categoria como Alimentos, Artículos,
        dentro de estos habrán subdatos o artículos y el número de artículos.

        Ej:
            http://localhost:8080/sgcnegocio/acopioPreparativos/agregarAcopioPreventivo
         {
             "usuario": "vsaavedrae12",
             "categoria": {
              "AlimentosAgua": [{
                "articulo": "Agua Potable",
                "numArticulos": "3Litros"
             }],
              "artículosSupervivencia": [{
                  "articulo": "Artículos de Higiene",
                "numArticulos": " 3 días"
             }]
        }
 }

        */


    @PostMapping("/agregarAcopioPreventivo")
    public ResponseEntity<?> agregarListadeAcopio(@RequestBody FormularioListadeAcopio acopio){
        FormularioListadeAcopio acop = this.formularioListadeAcopioService.agregar(acopio);
        return  new ResponseEntity<>(acop, HttpStatus.CREATED);
    }

    /*
        ENDPOINT activo
        Se debe enviar un JSON con los nuevos datos o los que se quiere modificar
        user, categoria, dentro de categoria como Alimentos, Artículos,
        dentro de estos habrán subdatos o artículos y el número de artículos.

        Ej:
            http://localhost:8080/sgcnegocio/acopioPreparativos/modificarAcopioPreventivo/6117ed30a7be7d74cd40466b
         {
             "usuario": "vsaavedrae12",
             "categoria": {
              "AlimentosAgua": [{
                "articulo": "Agua Potable",
                "numArticulos": "3Litros"
             }],
              "artículosSupervivencia": [{
                  "articulo": "Artículos de Higiene",
                "numArticulos": " 3 días"
             }]
        }
 }

        */
    @GetMapping("/modificarAcopioPreventivo/{id}")
    public ResponseEntity<?> modificarListadeAcopio(@RequestBody FormularioListadeAcopio listas, @PathVariable("id") String id){
        listas.setId(id);
        this.formularioListadeAcopioService.actualizar(listas);
        return  new ResponseEntity<>(listas,HttpStatus.CREATED);

    }
     /*
        ENDPOINT activo
         Se debe mandar el id del Formulario por la URL para poder eliminar el registro

        Ej:
            http://localhost:8080/sgcnegocio/acopioPreparativos/eliminarlistaAcopioPreventivo/6117ed30a7be7d74cd40466b


        */

    @DeleteMapping("/eliminarlistaAcopioPreventivo/{id}")
    public ResponseEntity<?> eliminarListadeAcopio (@PathVariable("id")String id){
        this.formularioListadeAcopioService.eliminarDocumento(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*
        ENDPOINT activo
         Se debe mandar el user  por la URL para poder buscar el registro

        Ej:
            http://localhost:8080/sgcnegocio/acopioPreparativos/buscarUsuarioListaAcopio/vsaavedrae12


        */

    @GetMapping("/buscarUsuarioListaAcopio/{user}")
    public ResponseEntity<?> buscarUsuarioListaAcopio(@PathVariable("user") String user){
        List<FormularioListadeAcopio> acopios = this.formularioListadeAcopioService.buscarporUsuario(user);
        return  new ResponseEntity<>(acopios, HttpStatus.OK);
    }


}
