package edu.uce.seguridad.controller;

import edu.uce.seguridad.model.FormularioRIP;
import edu.uce.seguridad.service.service.FormularioRIPService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sgcnegocio/formularioRIP")
@AllArgsConstructor
public class FormularioRIPController {

    private FormularioRIPService formularioRIPService;

    /*
    ENDPOINT que busca todos los RIPs de un usuario, el id del user se debe enviar por la URL
    Ej:
    http://localhost:8080/sgcnegocio/formularioRIP/buscarRIPsPorUser/vsaavedrae12

    - Si se encuentra al menos un RIP guardado devuelve un estado 200 - OK
    - Si no encuentra registros con respecto al usuario devuelve un estado 404 - NOT FOUND
    - En el caso de fallar la BD se devuelve un estado 500 - INTERNAL SERVER ERROR
    */
    @GetMapping("/buscarRIPsPorUser/{user}")
    public ResponseEntity<?> buscarRIPsPorUser(@PathVariable(value = "user") String user) {
        List<FormularioRIP> rips = this.formularioRIPService.buscarPorUser(user);
        return new ResponseEntity<>(rips, HttpStatus.OK);
    }

    /*
    ENDPOINT que busca la Prioridad  mas alto de todos los registros,
    el id del user se debe enviar por la URL
    Ej:
    http://localhost:8080/sgcnegocio/formularioRIP/buscarRIPMasAlta/vsaavedrae12

    - Si se encuentra al menos un RIP guardado devuelve un estado 200 - OK
    - Si no encuentra registros con respecto al usuario devuelve un estado 404 - NOT FOUND
    - En el caso de fallar la BD se devuelve un estado 500 - INTERNAL SERVER ERROR
    */
    @GetMapping("/buscarRIPMasAlta/{user}")
    public ResponseEntity<?> buscarRIPsMasAlta(@PathVariable(value = "user") String user) {
        List<FormularioRIP> rips = this.formularioRIPService.buscarPorUserFiltradoPorPrioridad(user);
        FormularioRIP rip = rips.get(0);
        return new ResponseEntity<>(rip, HttpStatus.OK);
    }

    /*
    ENDPOINT activo
    Se debe enviar un JSON con user, nombreRiesgo, impacto, probabilidad, prioridad
    * impacto, probabilidad y prioridad deberá tener valores por defecto:
    ** impacto, probabilidad: A : M  : B (Alto, Medio, Bajo)
    ** prioridad: 1-5 (El 5 es el valor que representa la prioridad más alta)
    Ej:
    {
        "user": "vsaavedrae12",
        "nombreRiesgo": "Incendio",
        "impacto": "M",
        "probabilidad": "A",
        "prioridad": 3
    }
    */
    @PostMapping("/agregarRIP")
    public ResponseEntity<?> agregarRIP(@RequestBody FormularioRIP rip) {
        FormularioRIP ripAdd = this.formularioRIPService.agregar(rip);
        return new ResponseEntity<>(ripAdd, HttpStatus.CREATED);
    }

    /*
    ENDPOINT activo
    Se debe mandar el id del RIP por la URL y los nuevos datos en formato JSON del RIP
    que se quiere actualizar.
    * impacto, probabilidad y prioridad deberá tener valores por defecto:
    ** impacto, probabilidad: A : M  : B (Alto, Medio, Bajo)
    ** prioridad: 1-5 (El 5 es el valor que representa la prioridad más alta)
    Ej:
    http://localhost:8080/sgcnegocio/formularioRIP/actualizarRIP/6110a5eafca8942357da77be
    {
        "user": "vsaavedrae12",
        "nombreRiesgo": "Incendio Update",
        "impacto": "B",
        "probabilidad": "B",
        "prioridad": 1
    }
    */
    @PutMapping("/actualizarRIP/{id}")
    public ResponseEntity<?> actualizarRIP (@RequestBody FormularioRIP rip, @PathVariable(value = "id") String id) {
        rip.set_id(id);
        FormularioRIP ripUpdated = this.formularioRIPService.actualizar(rip);
        return new ResponseEntity<>(ripUpdated, HttpStatus.OK);
    }

    /*
    ENDPOINT activo
    Se debe mandar unicamente el id del RIP en el URL para eliminar un RIP
    Ej:
    http://localhost:8080/sgcnegocio/formularioRIP/eliminarRIP/6110a85e4103d85937aa5b7d
    */
    @DeleteMapping("/eliminarRIP/{id}")
    public ResponseEntity<?> eliminarRIP(@PathVariable(value = "id") String id) {
        this.formularioRIPService.eliminarDocumento(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*
    ENDPOINT activo
    Se debe mandar unicamente el id del user en el URL para eliminar los RIPs
    Ej:
    http://localhost:8080/sgcnegocio/formularioRIP/eliminarRIPUser/vsaavedrae12
    */
    @DeleteMapping("/eliminarRIPUser/{user}")
    public ResponseEntity<?> eliminarRIPUser(@PathVariable(value = "user") String user) {
        this.formularioRIPService.eliminarPorUsusario(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
