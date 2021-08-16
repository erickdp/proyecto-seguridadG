package edu.uce.seguridad.controller;

import edu.uce.seguridad.model.ResumenDeEstrategias;
import edu.uce.seguridad.service.service.EstrategiaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/sgcnegocio/estrategiaResumen")
public class ResumenEstrategiaController {

    private EstrategiaService estrategiaService;

    @PostMapping("/agregarResumenEstrategia")
    public ResponseEntity<?> agregarResumen(@RequestBody ResumenDeEstrategias resumen){
        ResumenDeEstrategias resum= this.estrategiaService.agregar(resumen);
        return new ResponseEntity<>(resum, HttpStatus.CREATED);
    }

    @PutMapping("/actualizarEstrategia/{id}")
    public ResponseEntity<?> actualizarEstrategia(@RequestBody ResumenDeEstrategias resumen, @PathVariable("id")String id){
        resumen.set_id(id);
        this.estrategiaService.actualizar(resumen);
        return new ResponseEntity<>(resumen,HttpStatus.CREATED);
    }

    @DeleteMapping("/eliminarEstrategia/{id}")
    public ResponseEntity<?> eliminarEstrategia(@PathVariable("id") String id){
        this.estrategiaService.eliminarDocumento(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/buscarUsuarioEstrategia/{user}")
    public ResponseEntity<?> buscarPorUsuario(@PathVariable("user") String user){
        List<ResumenDeEstrategias> estrategia = this.estrategiaService.buscarporUsuario(user);
        return  new ResponseEntity<>(estrategia, HttpStatus.OK);
    }
}
