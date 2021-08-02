package edu.uce.seguridad.controller;


import edu.uce.seguridad.model.FormularioLiderazgo;
import edu.uce.seguridad.service.service.FormularioLiderazgoService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/sgcnegocio/formularioLiderazgo" )
@AllArgsConstructor

public class FormularioControllerLiderazgo {

    private FormularioLiderazgoService formularioLiderazgoService;

     /*
        ENDPOINT activo
        Se debe mandar el id por la URL y las nuevas respuestas en formato JSON del formulario Liderazgo que se quiere actualizar
        Ej:
            http://localhost:8080/sgcnegocio/formularioLiderazgo/agregarAlcance/buscarPorId/610018f6df7d521a7f6c5895
        {
            "user": "60fcb37db3c0630156388256",
            "personal": " campo Liderazgo abc",
            "negocio": "campo liderazgo 2abc"
        }
        */


    @PostMapping("/agregarLiderazgo")
    public ResponseEntity<?>agregarLiderazgo(@RequestBody FormularioLiderazgo liderazgo){
        FormularioLiderazgo lideraNew = this.formularioLiderazgoService.agregar(liderazgo);
        return new ResponseEntity<>(lideraNew, HttpStatus.CREATED);
    }
    /*
        ENDPOINT activo
        Se debe mandar el id por la URL y las nuevas respuestas en formato JSON del formulario Liderazgo que se quiere actualizar
        Ej:
            http://localhost:8080/sgcnegocio/formularioAlcance/actualizarLiderazgo/buscarPorId/6610018f6df7d521a7f6c5895
            "user": "60fcb37db3c0630156388256",
            "personal": "Campo1A ",
            "negocio": "Campo2B",

        }
        */

    @PutMapping("/actualizarLiderazgo/{id}")
    public ResponseEntity<?> actualizarLiderazgo(@RequestBody FormularioLiderazgo liderazgo, @PathVariable(value = "id")String id){
       liderazgo.set_id(id);
       FormularioLiderazgo liderazgoActualizado = this.formularioLiderazgoService.actualizar(liderazgo);
        return new ResponseEntity<>(liderazgoActualizado, HttpStatus.OK);
    }

    /*
        ENDPOINT activo
        Se debe mandar unicamente el id en el URL para eliminar el formulario
        Ej:
            http://localhost:8080/sgcnegocio/formularioLiderazgo/eliminarLiderazgo/buscarPorId/6610018f6df7d521a7f6c5895

        */
    @DeleteMapping("/eliminarLiderazgo/{id}")
    public ResponseEntity<?> eliminarLiderazgo(@PathVariable(value = "id") String id){
        this.formularioLiderazgoService.eliminarDocumento(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }

     /*
        ENDPOINT activo
       ENDPOINT que busca todos las respuestas del formulario Liderazgo de un usuario, el usuario se debe enviar por la URL
        Ej:
            http://localhost:8080/sgcnegocio/formularioLiderazgo/buscarPorId/610018f6df7d521a7f6c5895
        {
            "user": "60fcb37db3c0630156388256",
            "personal": "Campo1A ",
            "negocio": "Campo2B",

        }
        */

    @GetMapping("/buscarPorId/{id}")
    public  ResponseEntity<?> buscarPorid( @PathVariable (value = "id") String id){
        FormularioLiderazgo liderazgoF=this.formularioLiderazgoService.buscaPorId(id);
        return  new ResponseEntity<FormularioLiderazgo>(liderazgoF, HttpStatus.OK);
    }

    /*
        ENDPOINT activo
       ENDPOINT que busca todos las respuestas del formulario Liderazgo de un usuario, el usuario se debe enviar por la URL
        Ej:
            http://localhost:8080/sgcnegocio/formularioLiderazgo/buscarPorUser/60fcb37db3c0630156388256
        {
            "user": "60fcb37db3c0630156388256",
            "personal": "Campo1A ",
            "negocio": "Campo2B",

        }
        */

    @GetMapping("/buscarPorUser/{usuario}")
    public  ResponseEntity<?> buscarPorUsuario( @PathVariable (value = "usuario") String usuario){
        FormularioLiderazgo liderazgoFU  = this.formularioLiderazgoService.buscarFormularioPorUsuario(usuario);
        return  new ResponseEntity<FormularioLiderazgo>(liderazgoFU, HttpStatus.OK);
    }

}
