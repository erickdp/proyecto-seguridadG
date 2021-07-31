package edu.uce.seguridad.controller;

import edu.uce.seguridad.model.ListaEvaluacion;
import edu.uce.seguridad.service.service.ListaEvaluacionService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sgcnegocio/listaEvaluacion")
@AllArgsConstructor
public class ListaEvaluacionController {

    private ListaEvaluacionService listaEvaluacionService;

    /*
    ENDPOINT que busca todas las evaluaciones de un usuario, el id se debe enviar por la URL
    Ej:
    http://localhost:8080/sgcnegocio/listaEvaluacion/buscarEvaluacionPorUser/60fcb37db3c0630156388256

    * La lista ya se encuentra ordenada por el campo tipoCalidad

    - Si se encuentra al menos una evaluación guardada devuelve un estado 302 - FOUND
    - Si no encuentra registros con respecto al usuario devuelve un estado 404 - NOT FOUND
    - En el caso de fallar la BD se devuelve un estado 500 - INTERNAL SERVER ERROR
    * */
    @GetMapping("/buscarEvaluacionPorUser/{user}")
    public ResponseEntity<?> buscarEvaluacionporUser(@PathVariable(value = "user") String user) {
        List<ListaEvaluacion> evaluacion = this.listaEvaluacionService.buscarPorUserFiltrarPorTipoCalidad(user);
        return new ResponseEntity<>(evaluacion, HttpStatus.OK);
    }

    /*
    ENDPOINT activo
    Se debe enviar un JSON con user, tipoCalidad, tipo, caracteristica
    * tipoCalidad: se debe definir en el front valores unicos para este campo (comboBox)
    Ej:
    {
    "user": "60fcb37db3c0630156388256",
    "tipoCalidad": "bienes afectados",
    "tipo": "iMac",
    "caracteristica": "7 unidades"
    }
    */
    @PostMapping("/agregarEvaluacion")
    public ResponseEntity<?> agregarEvaluacion(@RequestBody ListaEvaluacion evaluacion) {
        ListaEvaluacion evaluacionNew = this.listaEvaluacionService.agregar(evaluacion);
        return new ResponseEntity<>(evaluacionNew, HttpStatus.CREATED);
    }

    /*
    ENDPOINT activo
    Se debe mandar el id por la URL y los nuevos datos en formato JSON de la evaluacion que se quiere actualizar
    Ej:
    http://localhost:8080/sgcnegocio/listaEvaluacion/actualizarEvaluacion/60fdc3969845fe5c999df4d3
    {
        "user": "60fcb37db3c0630156388256",
        "tipoCalidad": "bienes afectados updated",
        "tipo": "Monitor Samsung updated",
        "caracteristica": "20 unidades updated"
    }
    */
    @PutMapping("/actualizarEvaluacion/{id}")
    public ResponseEntity<?> actualizarEvaluacion(@RequestBody ListaEvaluacion evaluacion, @PathVariable(value = "id") String id) {
        evaluacion.set_id(id);
        ListaEvaluacion evalucionUpdated = this.listaEvaluacionService.actualizar(evaluacion);
        return new ResponseEntity<>(evalucionUpdated, HttpStatus.OK);
    }

    /*
    ENDPOINT activo
    Se debe mandar unicamente el id en el URL para eliminar una evaluacion
    Ej:
    http://localhost:8080/sgcnegocio/listaEvaluacion/eliminarEvaluacion/60fdc3969845fe5c999df4d3
    */
    @DeleteMapping("/eliminarEvaluacion/{id}")
    public ResponseEntity<?> eliminarEvaluacion(@PathVariable(value = "id") String id) {
        this.listaEvaluacionService.eliminarDocumento(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}


