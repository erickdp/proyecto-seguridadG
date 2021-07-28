package edu.uce.seguridad.controller;

import edu.uce.seguridad.model.FormularioAlcance;
import edu.uce.seguridad.model.FormularioLiderazgo;
import edu.uce.seguridad.service.service.FormularioAlcanceService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sgcnegocio/formularioAlcance" )
@CrossOrigin(origins ={"https://seguridad-sgcn.herokuapp.com", "http://localhost:8080"}, methods = {RequestMethod.GET, RequestMethod.POST,  RequestMethod.DELETE, RequestMethod.PUT})
@AllArgsConstructor
public class FormularioControllerAlcance {


    private FormularioAlcanceService formularioAlcanceService;


    /*
        ENDPOINT activo
        Se debe mandar el id por la URL y las nuevas respuestas en formato JSON del formulario alcance que se quiere actualizar
        Ej:
            http://localhost:8080/sgcnegocio/formularioAlcance/agregarAlcance/buscarPorId/60fcb37db3c0630156388256
        {
            "user": "60fcb37db3c0630156388256",
            "personal": "Campo1A ",
            "negocio": "Campo2B",
            "departamento": "Campo3C"
        }
        */

    @PostMapping("/agregarAlcance")
    public  ResponseEntity<?> agregarAlcance(@RequestBody FormularioAlcance alcance){
        FormularioAlcance newAlcance;
        Map<String, Object> response = new HashMap<>();
        try{
            newAlcance = this.formularioAlcanceService.agregar(alcance);
        }catch (DataAccessException dae){
            response.put("respuesta", dae.getMessage().concat(": ").concat(dae.getMostSpecificCause().getMessage()));
            response.put("mensaje", "Hubo un error al ingresar las respuestas en el Formulario Alcance.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("respuesta", "Se a agregado con exito");
        response.put("contacto", newAlcance);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
    /*
        ENDPOINT activo
        Se debe mandar el id por la URL y las nuevas respuestas en formato JSON del formulario alcance que se quiere actualizar
        Ej:
            http://localhost:8080/sgcnegocio/formularioAlcance/actualizarAlcance/buscarPorId/60fcb37db3c0630156388256
        {
            "user": "60fcb37db3c0630156388256",
            "personal": "Campo1 ",
            "negocio": "Campo2",
            "departamento": "Campo3",
        }
        */
    @PutMapping("/actualizarAlcance/{id}")
    public ResponseEntity<?> actualizarAlcance(@RequestBody FormularioAlcance alcance, @PathVariable(value = "id") String id ){
        Map<String, Object> response = new HashMap<>();
        try {
            if (  this.formularioAlcanceService.buscaPorId(id) != null){
                alcance.set_id(id);
                this.formularioAlcanceService.actualizar(alcance);
            }else{
                response.put("mensaje", "Hubo un error al actualizar el formulario. Pongase en contacto");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

        }catch(DataAccessException dae){
            response.put("respuesta", dae.getMessage().concat(": ").concat(dae.getMostSpecificCause().getMessage()));
            response.put("mensaje", "Hubo un error al ingresar las respuestas en el Formulario Alcance.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("respuesta", "Se actualizo con exito");
        response.put("contacto", alcance);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    /*
    ENDPOINT activo
    Se debe mandar unicamente el id en el URL para eliminar el formulario
    Ej:
    http://localhost:8080/sgcnegocio/formularioAlcance/eliminarAlcance/buscarPorId/60fcb37db3c0630156388256
    */

    @DeleteMapping("/eliminarAlcance/{id}")
    public  ResponseEntity<?> eliminarAlcance(@PathVariable(value = "id") String id){
        Map<String, Object> response = new HashMap<>();
        try{
            this.formularioAlcanceService.eliminarDocumento(id);
        }catch(DataAccessException dae){
            response.put("respuesta", dae.getMessage().concat(": ").concat(dae.getMostSpecificCause().getMessage()));
            response.put("mensaje", "Hubo un error al ingresar las respuestas en el Formulario Alcance.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("respuesta", "Se elimino con exito");
        response.put("contacto", id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*
   ENDPOINT que busca todos las respuestas del formulario Alcance de un usuario, el usuario se debe enviar por la URL
   Ej:
   http://localhost:8080/sgcnegocio/formularioAlcance/buscarPorId/60fcb37db3c0630156388256


   - Si se encuentra al menos un contacto guardado devuelve un estado 302 - FOUND
   - Si no encuentra registros con respecto al usuario devuelve un estado 404 - NOT FOUND
   - En el caso de fallar la BD se devuelve un estado 500 - INTERNAL SERVER ERROR
   */
    @GetMapping({"/buscarFormularioAlcancePorUser/{usuario}"})
    public ResponseEntity<?>buscarAlcanceporUser(@PathVariable(value = "usuario")String usuario){
       FormularioAlcance alcances;
        Map<String, Object> response = new HashMap<>();
        try{
            alcances =  this.formularioAlcanceService.buscarFormularioAlPorUsua(usuario);

        }catch(DataAccessException dae){
            response.put("respuesta", dae.getMessage().concat(": ").concat(dae.getMostSpecificCause().getMessage()));
            response.put("mensaje", "Hubo un error al buscar la información solicitada");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (alcances == null){
            response.put("respuesta", "No se han encontrado el formulario de alcance registrado para:".concat(usuario));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(alcances, HttpStatus.OK);

    }

    /*
    ENDPOINT que busca todos las respuestas del formulario Alcance de un usuario, el id se debe enviar por la URL
    Ej:
    http://localhost:8080/sgcnegocio/formularioAlcance/buscarPorId/60fcb37db3c0630156388256


    - Si se encuentra al menos un contacto guardado devuelve un estado 302 - FOUND
    - Si no encuentra registros con respecto al usuario devuelve un estado 404 - NOT FOUND
    - En el caso de fallar la BD se devuelve un estado 500 - INTERNAL SERVER ERROR
    */
    @GetMapping("/buscarPorId/{id}")
    public  ResponseEntity<?> buscarPorid( @PathVariable (value = "id") String id){
        FormularioAlcance liderazgoA=null;
        Map<String, Object> response = new HashMap<>();
        try{
            liderazgoA = this.formularioAlcanceService.buscaPorId(id);
        }catch(DataAccessException dae){
            response.put("respuesta", dae.getMessage().concat(": ").concat(dae.getMostSpecificCause().getMessage()));
            response.put("mensaje", "Hubo un error al buscar la información. Pongase en contacto");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (liderazgoA == null){
            response.put("respuesta", "No se encontro ningun registro");
            return  new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<FormularioAlcance>(liderazgoA, HttpStatus.OK);
    }





}
