package edu.uce.seguridad.controller;

import edu.uce.seguridad.model.Persona;
import edu.uce.seguridad.model.Respuesta;
import edu.uce.seguridad.model.Usuario;
import edu.uce.seguridad.repository.FormularioAlcanceRepository;
import edu.uce.seguridad.service.PersonaService;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sgcnegocio")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@AllArgsConstructor
public class ControladoPrincipal {

    private PersonaService personaService;

    private FormularioAlcanceRepository formularioAlcanceRepository;

    /*
    Funcion activa
    Se debe de enviar un JSON con el nombre de usuario y contrasena
    Ej:
    {
    "nombreUsuario": "salpalal57",
    "contrasena": "13EPL"
<<<<<<< HEAD
    }
=======
    }.
>>>>>>> ec2d63c8d1a1587b3007d2f2e201111e2190623e
    * */
    @PostMapping("/iniciarSesion")
    public Persona iniciarSesion(
            @RequestBody Usuario usuario) {
        return this.personaService.buscarPersonaPorUsuario(
                usuario.getNombreUsuario(),
                usuario.getContrasena());
    }

    /*
    Funcion activa
    Se debe enviar un JSON con nombre, apellido, organizacion, departamento, rol de usuario
    Ej:
    {
    "nombre": "Samantha",
    "apellido": "Jara",
    "organizacion": "UCE",
    "departamento": "INGENIERIA",
    "usuario": {
        "role": "REPRESENTANTE"
    }
    }
    * */
    @PostMapping("/agregarUsuario")
    public Persona agregarUsuario(
            @RequestBody Persona persona) {
        return this.personaService.agregar(persona);
    }

    @GetMapping("/buscarPorIdentificador/{identificador}")
    public Persona buscarPorIdentificador(@PathVariable String identificador) {
        return this.personaService.buscaPorId(identificador);
    }

}
