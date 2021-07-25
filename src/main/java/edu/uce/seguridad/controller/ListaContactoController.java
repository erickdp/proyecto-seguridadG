package edu.uce.seguridad.controller;

import edu.uce.seguridad.model.ListaContacto;
import edu.uce.seguridad.service.service.ListaContactoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sgcnegocio/listaContacto")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@AllArgsConstructor
public class ListaContactoController {

    private ListaContactoService listaContactoService;

    @GetMapping
    public List<ListaContacto> test() {
        return this.listaContactoService.buscarPorUser("654654653213");
    }

}
