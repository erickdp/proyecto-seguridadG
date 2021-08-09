package edu.uce.seguridad.controller;

import edu.uce.seguridad.model.EstimacionDano;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sgcnegocio/estimaciondano")
@AllArgsConstructor
public class EstimacionDanoController {

    private EstimacionDano estimacionDano;

    @GetMapping
    public EstimacionDano getEstimacionDano() {
        return estimacionDano;
    }
}
