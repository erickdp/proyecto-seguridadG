package edu.uce.seguridad.controller;


import edu.uce.seguridad.model.FormularioAlcance;
import edu.uce.seguridad.model.FormularioLiderazgo;
import edu.uce.seguridad.service.FormularioLiderazgoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sgcnegocio/formularioLiderazgo" )
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST,  RequestMethod.DELETE, RequestMethod.PUT})
@AllArgsConstructor

public class FormularioControllerLiderazgo {

    private FormularioLiderazgoService formularioLiderazgoService;

    @GetMapping
    public List<FormularioLiderazgo> test(){
        return this.formularioLiderazgoService.buscarTodos();
    }
}
