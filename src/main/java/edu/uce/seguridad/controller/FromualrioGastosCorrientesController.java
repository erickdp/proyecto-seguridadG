package edu.uce.seguridad.controller;

import edu.uce.seguridad.model.GastosCorrientes;
import edu.uce.seguridad.service.service.GastosCorrienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/sgcnegocio/gastosCorrientes")
public class FromualrioGastosCorrientesController {

    private GastosCorrienteService gastosCorrienteService;

    @PostMapping("/agregarGastosCorriente")
    public ResponseEntity<?> agrearGastos(@RequestBody GastosCorrientes gastos){
        GastosCorrientes gasto = this.gastosCorrienteService.agregar(gastos);
        return new ResponseEntity<>(gasto, HttpStatus.CREATED);
    }

    @PutMapping("/actualizarGastosCorriente/{id}")
    public ResponseEntity<?> actualizarGastos(@RequestBody GastosCorrientes gastos, @PathVariable("id") String id){
        gastos.set_id(id);
        this.gastosCorrienteService.actualizar(gastos);
        return new ResponseEntity<>(gastos,HttpStatus.OK);
    }

    @DeleteMapping("/eliminarGasto/{id}")
    public ResponseEntity<?> eliminarGasto(@PathVariable(value = "id")String id){
        this.gastosCorrienteService.eliminarDocumento(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/buscarUsuarioGastos/{user}")
    public ResponseEntity<?> buscaporUsuario(@PathVariable("user") String user){
        GastosCorrientes gasto= this.gastosCorrienteService.buscarGastosCorrientesPorUsuario(user);
        return new ResponseEntity<>(gasto,HttpStatus.OK);
    }
}
