package edu.uce.seguridad.controller;

import edu.uce.seguridad.model.EstatusFinanciero;
import edu.uce.seguridad.service.service.EstatusFinancieroService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sgcnegocio/estatus")
@AllArgsConstructor
public class EstatusFinancieroController {

    private EstatusFinancieroService financieroService;

    @GetMapping("/buscarEstatusPorUsuario/{usuario}")
    public ResponseEntity<?> buscarPorUsuario(@PathVariable("usuario") String nombreUsuario) {
        EstatusFinanciero estatus = financieroService.buscarPorUsuario(nombreUsuario);
        return new ResponseEntity<>(estatus, HttpStatus.OK);
    }

}
