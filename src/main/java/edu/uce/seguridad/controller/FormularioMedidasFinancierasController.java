package edu.uce.seguridad.controller;

import edu.uce.seguridad.model.FormularioMedidasFinancieras;
import edu.uce.seguridad.service.service.FormularioMedidasFinancierasService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sgcnegocio/formularioMedidasFinancieras")
@AllArgsConstructor
public class FormularioMedidasFinancierasController {
    private FormularioMedidasFinancierasService formularioMedidasFinancierasService;
    
    /*
  
    * La lista ya se encuentra ordenada por el campo tipoMedidasFinancieras

    - Si se encuentra al menos un contacto guardado devuelve un estado 302 - FOUND
    - Si no encuentra registros con respecto al usuario devuelve un estado 404 - NOT FOUND
    - En el caso de fallar la BD se devuelve un estado 500 - INTERNAL SERVER ERROR
    */
    
    @GetMapping("/buscarMedidasFinancierasPorUser/{user}")
    public ResponseEntity<?> buscarMedidasFinancierasPorUser(@PathVariable(value = "user") String user) {
        List<FormularioMedidasFinancieras> contacto = this.formularioMedidasFinancierasService.buscarPorUserFiltrarPorMedidasFinancieras(user);
        return new ResponseEntity<>(contacto, HttpStatus.OK);
    }
    /*
    ENDPOINT activo
    Se debe enviar un JSON con user, medidasFinancieras, monto, detalles
    * Contacto: se debe definir en el front valores unicos para este campo (comboBox)
    Ej:
     {
        "user": "vsaavedrai30",
        "medidasFinancieras": "prestamo banco",
        "monto": "100.100",
        "detalles": "tasa de interes del 2%"
    }
    */
    @PostMapping("/agregarMedidasFinancieras")
    public ResponseEntity<?> agregarMedidasFinancieras(@RequestBody FormularioMedidasFinancieras contacto) {
        FormularioMedidasFinancieras contactoNew = this.formularioMedidasFinancierasService.agregar(contacto);      
        return new ResponseEntity<>(contactoNew, HttpStatus.CREATED);
    }
    
    @PutMapping("/actualizarMedidasFinancieras/{id}")
    public ResponseEntity<?> actualizarMedidasFinancieras(@RequestBody FormularioMedidasFinancieras contacto, @PathVariable(value = "id") String id) {
        contacto.set_id(id);
        FormularioMedidasFinancieras contactoActualizado = this.formularioMedidasFinancierasService.actualizar(contacto);
        return new ResponseEntity<>(contactoActualizado, HttpStatus.OK);
    }
    
    @DeleteMapping("/eliminarMedidasFinancieras/{id}")
    public ResponseEntity<?> eliminarMedidasFinancieras(@PathVariable(value = "id") String id) {
        this.formularioMedidasFinancierasService.eliminarDocumento(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
