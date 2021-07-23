package edu.uce.seguridad.controller;

import edu.uce.seguridad.service.service.ListaEvaluacionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sgcnegocio/listaEvaluacion")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@AllArgsConstructor
public class ListaEvaluacionController {

    private ListaEvaluacionService listaEvaluacionService;

    @GetMapping
    public String test() {
        return "Hello listaEvaluacionService";
    }

}


