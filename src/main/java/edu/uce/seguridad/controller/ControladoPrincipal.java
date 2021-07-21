package edu.uce.seguridad.controller;

import edu.uce.seguridad.model.FormularioAlcance;
import edu.uce.seguridad.model.Persona;
import edu.uce.seguridad.model.Respuesta;
import edu.uce.seguridad.model.Usuario;
import edu.uce.seguridad.repository.FormularioAlcanceRepository;
import edu.uce.seguridad.repository.PersonaRepository;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/seg")
@AllArgsConstructor
public class ControladoPrincipal {

    private PersonaRepository repository;

    private FormularioAlcanceRepository formularioAlcanceRepository;

    @GetMapping("/per")
    public Persona crearPersona() {
        Persona persona = new Persona();
        persona.set_id("1");
        Usuario usuario = new Usuario();
        persona.setApellido("Alpala");
        persona.setApellido("Jr");
        persona.setDepartamento("Tecnología");
        persona.setOrganizacion("UCE");
        usuario.setUsuario("salpala");
        usuario.setContrasena("123");
        usuario.setRole("ADMIN");
        persona.setUsuario(usuario);
        return repository.save(persona);
    }

    @GetMapping("/formulario")
    public FormularioAlcance getForm() {
        FormularioAlcance form = new FormularioAlcance();
        form.set_id("1");
        form.setPregunta("Nombre de tus perros");
        Respuesta respuesta = new Respuesta();
        respuesta.setNombreUsuario("salpala");
        respuesta.setRespuestas(Arrays.asList("Juan", "Mario", "Pedro", "Saul"));
        Respuesta respuestaA = new Respuesta();
        respuestaA.setRespuestas(Arrays.asList("Juan", "Mario"));
        Respuesta respuestaB = new Respuesta();
        respuestaB.setRespuestas(Arrays.asList("Saul"));
        form.setRespuestas(Arrays.asList(respuesta, respuestaB, respuestaA));
        return formularioAlcanceRepository.save(form);
    }

    @GetMapping("/obtenerP")
    public FormularioAlcance getFormP() {
        FormularioAlcance form = this.formularioAlcanceRepository.findBy_id(new ObjectId("60f75e3c88a4bd71734f8115"));
        List<Respuesta> respuestas = form.getRespuestas();
        Respuesta respuesta = new Respuesta();
        respuesta.setNombreUsuario("sam");
        respuesta.setRespuestas(Arrays.asList("Juan", "Saul"));
        respuestas.add(respuesta);
        form.setRespuestas(respuestas);
        return formularioAlcanceRepository.save(form);
    }

}
