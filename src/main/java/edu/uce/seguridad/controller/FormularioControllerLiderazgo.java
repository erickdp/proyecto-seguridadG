package edu.uce.seguridad.controller;


import edu.uce.seguridad.model.FormularioLiderazgo;
import edu.uce.seguridad.model.Usuario;
import edu.uce.seguridad.service.service.FormularioLiderazgoService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController

@RequestMapping("/sgcnegocio/formularioLiderazgo" )
@CrossOrigin(origins = "{\"https://seguridad-sgcn.herokuapp.com\", \"http://localhost:8080\"}", methods = {RequestMethod.GET, RequestMethod.POST,  RequestMethod.DELETE, RequestMethod.PUT})
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
        FormularioLiderazgo newliderazgo;
        Map<String, Object> response = new HashMap<>();
        try{
           newliderazgo= this.formularioLiderazgoService.agregar(liderazgo);
        }catch ( DataAccessException dae){
            response.put("respuesta", dae.getMessage().concat(": ").concat(dae.getMostSpecificCause().getMessage()));
            response.put("mensaje", "Hubo un error al ingresar nuevos datos. Pongase en contacto");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("respuesta", "Se agrego correctamente");
        response.put("contacto", newliderazgo);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
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
        Map<String, Object> response = new HashMap<>();

        try{
            if (  this.formularioLiderazgoService.buscaPorId(id) != null){
                liderazgo.set_id(id);
                this.formularioLiderazgoService.actualizar(liderazgo);
            }else{
                response.put("mensaje", "Hubo un error al actualizar el formulario. Pongase en contacto");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        }catch (DataAccessException dae){
            response.put("respuesta", dae.getMessage().concat(": ").concat(dae.getMostSpecificCause().getMessage()));
            response.put("mensaje", "Hubo un error al actualizar el formulario. Pongase en contacto");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("respuesta", "Se actualizo correctamente");
        response.put("contacto", liderazgo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*
        ENDPOINT activo
        Se debe mandar unicamente el id en el URL para eliminar el formulario
        Ej:
            http://localhost:8080/sgcnegocio/formularioLiderazgo/eliminarLiderazgo/buscarPorId/6610018f6df7d521a7f6c5895

        */
    @DeleteMapping("/eliminarLiderazgo/{id}")
    public ResponseEntity<?> eliminarLiderazgo(@PathVariable(value = "id") String id){
        Map<String, Object> response = new HashMap<>();
        try{
            this.formularioLiderazgoService.eliminarDocumento(id);
        }catch (DataAccessException dae){
            response.put("respuesta", dae.getMessage().concat(": ").concat(dae.getMostSpecificCause().getMessage()));
            response.put("mensaje", "Hubo un error al borrar el formulario. Pongase en contacto");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("respuesta", "Se elimino correctamente");
        response.put("contacto", id);
        return new ResponseEntity<>(response, HttpStatus.OK);
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
        FormularioLiderazgo liderazgoF=null;
        Map<String, Object> response = new HashMap<>();
        try{
            liderazgoF = this.formularioLiderazgoService.buscaPorId(id);
        }catch(DataAccessException dae){
            response.put("respuesta", dae.getMessage().concat(": ").concat(dae.getMostSpecificCause().getMessage()));
            response.put("mensaje", "Hubo un error al buscar la información. Pongase en contacto");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (liderazgoF == null){
            response.put("respuesta", "No se encontro ningun registro");
            return  new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
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
        FormularioLiderazgo liderazgoFU ;
        Map<String, Object> response = new HashMap<>();
        try{
            liderazgoFU = this.formularioLiderazgoService.buscarFormularioPorUsuario(usuario);
        }catch(DataAccessException dae){
            response.put("respuesta", dae.getMessage().concat(": ").concat(dae.getMostSpecificCause().getMessage()));
            response.put("mensaje", "Hubo un error al buscar la información. Pongase en contacto");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (liderazgoFU == null){
            response.put("respuesta",  "No se han encontrado el formulario de Liderazgo registrado para:".concat(usuario));
            return  new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<FormularioLiderazgo>(liderazgoFU, HttpStatus.OK);
    }

}
