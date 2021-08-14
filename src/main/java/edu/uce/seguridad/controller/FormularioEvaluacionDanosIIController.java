package edu.uce.seguridad.controller;

import edu.uce.seguridad.model.FormularioEvaluacionDanosII;
import edu.uce.seguridad.service.service.FormularioEvaluacionDanosIIService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sgcnegocio/evaluacionDanos2")
@AllArgsConstructor
public class FormularioEvaluacionDanosIIController {

    private FormularioEvaluacionDanosIIService evaluacionDanosIIService;

    @GetMapping("/buscarPorUser/{user}")
    public ResponseEntity<?> buscarEvaluacion2PorUser(@PathVariable("user") String user) {
        List<FormularioEvaluacionDanosII> evaluaciones = this.evaluacionDanosIIService.buscarPorUsuario(user);
        return new ResponseEntity<>(evaluaciones, HttpStatus.OK);
    }

    @PostMapping("/agregarEvaluacion2")
    public ResponseEntity<?> agregarEvaluacion(@RequestBody FormularioEvaluacionDanosII formEv) {
        FormularioEvaluacionDanosII eva = this.evaluacionDanosIIService.agregar(formEv);
        return new ResponseEntity<>(eva, HttpStatus.CREATED);
    }

    @PutMapping("/actualizarEvaluacion2/{id}")
    public ResponseEntity<?> actualizarEvaluacion2 (@RequestBody FormularioEvaluacionDanosII eva ,@PathVariable("id") String id){
        eva.set_id(id);
        this.evaluacionDanosIIService.actualizar(eva);
        return new ResponseEntity<>(eva, HttpStatus.CREATED);
    }

    @DeleteMapping("/eliminarEvaluacion/{id}")
    public ResponseEntity<?> eliminarEvaluacion2 (@PathVariable("id") String id) {
        this.evaluacionDanosIIService.eliminarDocumento(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
