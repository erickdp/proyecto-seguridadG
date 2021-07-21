package edu.uce.seguridad.controller;

import edu.uce.seguridad.model.FormularioAlcance;
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
    }
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

    //Probar con este metodo
    @GetMapping("/per")
    public FormularioAlcance crearPersona() {
        FormularioAlcance formulario = this.formularioAlcanceRepository.findByPregunta("Nombre de tus perros");
        List<Respuesta> respuestas = formulario.getRespuestas();
//        Filtro las preguntas por el usuario que me interesa, no importa si tiene varias respuestas como por ejemplo en operador
        formulario.setRespuestas(respuestas.stream().filter(r -> r.getNombreUsuario().equals("sam")).collect(Collectors.toList()));
        return formulario;
    }

    @GetMapping("/updateRespuesta")
    public FormularioAlcance getFormP() {
        FormularioAlcance form = this.formularioAlcanceRepository.findBy_id(new ObjectId("60f814d323f96f7906931fd3"));
        List<Respuesta> respuestas = form.getRespuestas();
        Respuesta respuesta = new Respuesta();
        respuesta.setNombreUsuario("sam");
        respuesta.setRespuesta(Arrays.asList("Juan", "Saul"));
        respuestas.add(respuesta);
        form.setRespuestas(respuestas);
        return formularioAlcanceRepository.save(form);
    }

    @GetMapping("/addPregunta")
    public FormularioAlcance addNewPregunta() {
        FormularioAlcance form = new FormularioAlcance();
        form.setPregunta("Como te llamas");
        Respuesta resp = new Respuesta();
        resp.setNombreUsuario("alpala");
        resp.setRespuesta(Arrays.asList("Saul", "Junior"));

        Respuesta resp2 = new Respuesta();
        resp2.setNombreUsuario("erick");
        resp2.setRespuesta(Arrays.asList("Erick", "Enrique"));
        // nuevo colaborador
        form.setRespuestas(Arrays.asList(resp, resp2));
        return formularioAlcanceRepository.save(form);
    }
}
