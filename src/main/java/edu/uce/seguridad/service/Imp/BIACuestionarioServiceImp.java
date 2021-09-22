package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.EliminacionException;
import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.BIACuestionario;
import edu.uce.seguridad.model.Persona;
import edu.uce.seguridad.repository.BIACuestionarioRepository;
import edu.uce.seguridad.repository.BIAPreguntaRepository;
import edu.uce.seguridad.repository.PersonaRepository;
import edu.uce.seguridad.service.service.BIACuestionarioService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class BIACuestionarioServiceImp implements BIACuestionarioService {

    private PersonaRepository personaRepository;

    private BIACuestionarioRepository biaCuestionarioRepository;

    private BIAPreguntaRepository biaPreguntaRepository;

    @Override
    public List<BIACuestionario> buscarTodos() throws NoEncontradoExcepcion {
        List<BIACuestionario> list = this.biaCuestionarioRepository.findAll();
        if (list.isEmpty()) {
            throw new NoEncontradoExcepcion("mensaje", "No se han encontrado registros");
        }
        return list;
    }

    @Override
    public BIACuestionario agregar(BIACuestionario pojo) throws DataAccessException {
        return this.biaCuestionarioRepository.save(pojo);
    }

    @Override
    public BIACuestionario actualizar(BIACuestionario pojo) throws DataAccessException {
        return this.biaCuestionarioRepository.save(pojo);
    }

    @Override
    public BIACuestionario buscaPorId(String identificador) throws NoEncontradoExcepcion {
        Optional<BIACuestionario> biaCuestionario = this.biaCuestionarioRepository.findByUsuario(identificador);
        if (biaCuestionario.isPresent()) {
            return biaCuestionario.get();
        }
        throw new NoEncontradoExcepcion("mensaje", "No se han encontrado registros para ".concat(identificador));
    }

    @Override
    public void eliminarDocumento(String identificador) throws EliminacionException {
        this.biaCuestionarioRepository.findByUsuario(identificador).ifPresent(this.biaCuestionarioRepository::delete);
    }

    // Metodo que permite obtener el promedio de las preguntas de manera dinamica
    @Override
    public Map<String, Double> obtenerPromedioCuestionario(String organizacion) {

        List<Persona> personas;
        if (organizacion == null) {
            personas = this.personaRepository.findPersonaByRole("DEPARTAMENTO");
        } else {
            personas = this.personaRepository.findPersonaByRolAndOrganization("DEPARTAMENTO", organizacion);
        }

        HashMap<String, Integer> totalPreguntas = new HashMap<>();
        HashMap<String, Integer> totalParticipantes = new HashMap<>();

        for (int i = 0; i < this.biaPreguntaRepository.findAll().size(); i++) { // Defino el numero de preguntas que existen
            totalPreguntas.put("pregunta_" + (i + 1), 0);
            totalParticipantes.put("participantes_" + (i + 1), 0);
        }

        HashMap<String, Double> promedio = new HashMap<>();

        if (!personas.isEmpty()) {

            for (Persona persona :
                    personas) {
                Optional<BIACuestionario> cuestionario = this.biaCuestionarioRepository.findByUsuario(persona.getUsuario().getNombreUsuario());
                if (cuestionario.isPresent() && cuestionario.get().getRespuestas() != null && !cuestionario.get().getRespuestas().isEmpty()) { // Debe de tener inicializado las respuestas, sino no se contabilizan sus calificaciones
                    // Si tiene inicializado sus preguntas pero sin ninguna respuesta tampoco, no se contabiliza para el promedio total
                    List<Integer> respuestas = cuestionario.get().getRespuestas();

                    for (int i = 0; i < respuestas.size(); i++) { // Incremento el puntaje a las preguntas existentes

                        Integer calificacion = respuestas.get(i);

                        if (calificacion.equals(0)) { // La calificacion debe de ser distinta de 0 para contabilizar en el promedio
                            continue;
                        }

                            Integer sumaPregunta = totalPreguntas.get("pregunta_" + (i + 1));

                        Integer actualizarParticipantes = totalParticipantes.get("participantes_" + (i + 1));// El contado de participantes se actualiza
                        totalParticipantes.put("participantes_" + (i + 1),
                                ++actualizarParticipantes
                            );

                            sumaPregunta += respuestas.get(i);

                            totalPreguntas.put("pregunta_" + (i + 1), sumaPregunta);

                    }
                }
            }

            for (int i = 0; i < totalPreguntas.size(); i++) {
                Integer preguntaSumaTotal = totalPreguntas.get("pregunta_" + (i + 1));
                log.info("Suma calificaciones total de preugunta " + (i + 1) + " " + preguntaSumaTotal);
                Integer participantesSumaTotal = totalParticipantes.get("participantes_" + (i + 1));
                log.info("Suma participantes total de pregunta " + (i + 1) + " " + participantesSumaTotal);
                promedio.put("pregunta_" + (i + 1) + "_promedio",  Math.round(((double) preguntaSumaTotal / participantesSumaTotal) * 100.0) / 100.0);
            }

            return promedio;
        }
        promedio.put("sin_registros", 0d);
        return promedio;
    }

    @Override
    public void eliminarRespuestaFormularioBIAC(String nombreUsuario) {
        this.biaCuestionarioRepository.findByUsuario(nombreUsuario).ifPresent(this.biaCuestionarioRepository::delete);
    }

}
