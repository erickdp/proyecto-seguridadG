package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.EliminacionException;
import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.BIACuestionario;
import edu.uce.seguridad.model.Persona;
import edu.uce.seguridad.repository.BIACuestionarioRepository;
import edu.uce.seguridad.repository.PersonaRepository;
import edu.uce.seguridad.service.service.BIACuestionarioService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BIACuestionarioServiceImp implements BIACuestionarioService {

    private PersonaRepository personaRepository;

    private BIACuestionarioRepository biaCuestionarioRepository;

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

    @Override
    public Map<String, Double> obtenerPromedioCuestionario(String organizacion) {
        List<Persona> personas = this.personaRepository.findPersonaByRolAndOrganization("DEPARTAMENTO", organizacion);

        int pregunta1 = 0;
        int pregunta2 = 0;
        int pregunta3 = 0;
        int pregunta4 = 0;
        int pregunta5 = 0;
        int pregunta6 = 0;
        int pregunta7 = 0;
        int pregunta8 = 0;
        int pregunta9 = 0;
        int pregunta10 = 0;
        int pregunta11 = 0;
        int pregunta12 = 0;

        for (Persona persona :
                personas) {
            Optional<BIACuestionario> cuestionario = this.biaCuestionarioRepository.findByUsuario(persona.getUsuario().getNombreUsuario());
            if(cuestionario.isPresent()) {
                List<Byte> respuestas = cuestionario.get().getRespuestas();

                pregunta1 += respuestas.get(0);
                pregunta2 += respuestas.get(1);
                pregunta3 += respuestas.get(2);
                pregunta4 +=respuestas.get(3);
                pregunta5 += respuestas.get(4);
                pregunta6 += respuestas.get(5);
                pregunta7 += respuestas.get(6);
                pregunta8 += respuestas.get(7);
                pregunta9 += respuestas.get(8);
                pregunta10 += respuestas.get(9);
                pregunta11 += respuestas.get(10);
                pregunta12 += respuestas.get(11);

            }
        }

        HashMap<String, Double> promedio = new HashMap<>();
        promedio.put("promedio_1", pregunta1/personas.size() + 0d);
        promedio.put("promedio_2", pregunta2/personas.size() + 0d);
        promedio.put("promedio_3", pregunta3/personas.size() + 0d);
        promedio.put("promedio_4", pregunta4/personas.size() + 0d);
        promedio.put("promedio_5", pregunta5/personas.size() + 0d);
        promedio.put("promedio_6", pregunta6/personas.size() + 0d);
        promedio.put("promedio_7", pregunta7/personas.size() + 0d);
        promedio.put("promedio_8", pregunta8/personas.size() + 0d);
        promedio.put("promedio_9", pregunta9/personas.size() + 0d);
        promedio.put("promedio_10", pregunta10/personas.size() + 0d);
        promedio.put("promedio_11", pregunta11/personas.size() + 0d);
        promedio.put("promedio_12", pregunta12/personas.size() + 0d);

        return promedio;
    }
}
