package edu.uce.seguridad.controller;

import edu.uce.seguridad.model.FormularioAlcance;
import edu.uce.seguridad.service.service.FormularioAlcanceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sgcnegocio/formularioAlcance" )
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST,  RequestMethod.DELETE, RequestMethod.PUT})
@AllArgsConstructor
public class FormularioControllerAlcance {


    private FormularioAlcanceService formularioAlcanceService;

    @GetMapping
    public List<FormularioAlcance> test(){
        return this.formularioAlcanceService.buscarTodos();
    }




}
