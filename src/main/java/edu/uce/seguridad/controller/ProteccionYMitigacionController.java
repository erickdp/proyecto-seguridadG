package edu.uce.seguridad.controller;

import edu.uce.seguridad.model.ProteccionYMitigacion;
import edu.uce.seguridad.service.service.ProteccionYMitigacionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/sgcnegocio/proteccionMitigacion")
@AllArgsConstructor
public class ProteccionYMitigacionController {

    private ProteccionYMitigacionService proteccionYMitigacionService;

    @GetMapping
    private ResponseEntity<?> getPYM() {
        List<ProteccionYMitigacion> proteccionYMitigacions = this.proteccionYMitigacionService.buscarTodos();
        return new ResponseEntity<List<ProteccionYMitigacion>>(proteccionYMitigacions, OK);
    }

    @GetMapping("/{nombreUsuario}")
    private ResponseEntity<?> getPYMByUser(
            @PathVariable String nombreUsuario
    ) {
        Collection<ProteccionYMitigacion> proteccionYMitigacions = this.proteccionYMitigacionService.obtenerPorUsuario(nombreUsuario);
        return new ResponseEntity<Collection<ProteccionYMitigacion>>(proteccionYMitigacions, OK);
    }

    @PutMapping
    private ResponseEntity<?> actualizarForm(
            @RequestBody ProteccionYMitigacion proteccionYMitigacion
    ) {
        ProteccionYMitigacion proteccionYMitigacions = this.proteccionYMitigacionService.actualizar(proteccionYMitigacion);
        return new ResponseEntity<ProteccionYMitigacion>(proteccionYMitigacions, OK);
    }

    @GetMapping("/{nombreUsuario}/{riesgo}")
    private ResponseEntity<?> getPYMPorUsuarioYRiesgo(
            @PathVariable("nombreUsuario") String user,
            @PathVariable("riesgo") String riesgo
    ) {
        ProteccionYMitigacion proteccionYMitigacion = this.proteccionYMitigacionService.obtenerPorFormPorRiesgoYUsuario(riesgo, user);
        return new ResponseEntity<ProteccionYMitigacion>(proteccionYMitigacion, OK);
    }
}
