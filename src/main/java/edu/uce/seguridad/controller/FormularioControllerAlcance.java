package edu.uce.seguridad.controller;

import edu.uce.seguridad.model.FormularioAlcance;
import edu.uce.seguridad.service.service.FormularioAlcanceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sgcnegocio/formularioAlcance" )
@AllArgsConstructor
public class FormularioControllerAlcance {


    private FormularioAlcanceService formularioAlcanceService;


    /*
        ENDPOINT activo
        Se debe mandar el id por la URL y las nuevas respuestas en formato JSON del formulario alcance que se quiere actualizar
        Ej:
            http://localhost:8080/sgcnegocio/formularioAlcance/agregarAlcance/buscarPorId/60fcb37db3c0630156388256
        {
            "user": "kevin22",
            "personal": "Campo1A ",
            "negocio": "Campo2B",
            "departamento": "Campo3C"
        }
        */

    @PostMapping("/agregarAlcance")
    public  ResponseEntity<?> agregarAlcance(@RequestBody FormularioAlcance alcance){
       FormularioAlcance alcanceNew= this.formularioAlcanceService.agregar(alcance);
       return  new ResponseEntity<>(alcanceNew, HttpStatus.CREATED);
        }
    /*
        ENDPOINT activo
        Se debe mandar el id por la URL y las nuevas respuestas en formato JSON del formulario alcance que se quiere actualizar
        Ej:
            http://localhost:8080/sgcnegocio/formularioAlcance/actualizarAlcance/buscarPorId/60fcb37db3c0630156388256
        {
            "user": "kevin22",
            "personal": "Campo1 ",
            "negocio": "Campo2",
            "departamento": "Campo3",
        }
        */
    @PutMapping("/actualizarAlcance/{id}")
    public ResponseEntity<?> actualizarAlcance(@RequestBody FormularioAlcance alcance, @PathVariable(value = "id") String id ){
        alcance.set_id(id);
        FormularioAlcance alcanceActualizado= this.formularioAlcanceService.actualizar(alcance);
        return new ResponseEntity<>(alcanceActualizado, HttpStatus.OK);
    }
    /*
    ENDPOINT activo
    Se debe mandar unicamente el id en el URL para eliminar el formulario
    Ej:
    http://localhost:8080/sgcnegocio/formularioAlcance/eliminarAlcance/buscarPorId/60fcb37db3c0630156388256
    */

    @DeleteMapping("/eliminarAlcance/{id}")
    public  ResponseEntity<?> eliminarAlcance(@PathVariable(value = "id") String id){
        this.formularioAlcanceService.eliminarDocumento(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*
   ENDPOINT que busca todos las respuestas del formulario Alcance de un usuario, el usuario se debe enviar por la URL
   Ej:
   http://localhost:8080/sgcnegocio/formularioAlcance/buscarPorId/kevin22


   - Si se encuentra al menos un contacto guardado devuelve un estado 302 - FOUND
   - Si no encuentra registros con respecto al usuario devuelve un estado 404 - NOT FOUND
   - En el caso de fallar la BD se devuelve un estado 500 - INTERNAL SERVER ERROR
   */
    @GetMapping({"/buscarFormularioAlcancePorUser/{usuario}"})
    public ResponseEntity<?>buscarAlcanceporUser(@PathVariable(value = "usuario")String usuario){
        FormularioAlcance alcanceUsuario = this.formularioAlcanceService.buscarFormularioAlPorUsua(usuario);
        return new ResponseEntity<>(alcanceUsuario, HttpStatus.OK);

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
        FormularioAlcance liderazgoA=this.formularioAlcanceService.buscaPorId(id);;
        return  new ResponseEntity<FormularioAlcance>(liderazgoA, HttpStatus.OK);
    }





}
