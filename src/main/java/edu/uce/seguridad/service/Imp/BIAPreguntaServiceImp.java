package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.EliminacionException;
import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.BIAPregunta;
import edu.uce.seguridad.repository.BIACuestionarioRepository;
import edu.uce.seguridad.repository.BIAPreguntaRepository;
import edu.uce.seguridad.repository.PersonaRepository;
import edu.uce.seguridad.service.service.BIAPreguntaService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// By Erick
@Service
@AllArgsConstructor
public class BIAPreguntaServiceImp implements BIAPreguntaService {

    private BIAPreguntaRepository biaPreguntaRepository;

    private BIACuestionarioRepository biaCuestionarioRepository;

    private PersonaRepository personaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<BIAPregunta> buscarTodos() throws NoEncontradoExcepcion {
        List<BIAPregunta> list = this.biaPreguntaRepository.findAll();
        if (list.isEmpty()) {
            throw new NoEncontradoExcepcion("mensaje", "No se han encontrado registros");
        }
        return list;
    }

    @Override
    @Transactional
    public BIAPregunta agregar(BIAPregunta pojo) throws DataAccessException {

        BIAPregunta nuevaPregunta = this.biaPreguntaRepository.insert(pojo);

        this.personaRepository.findPersonaByRole("DEPARTAMENTO").forEach(persona -> {
            this.biaCuestionarioRepository.findByUsuario(persona.getUsuario().getNombreUsuario()).ifPresent(cuestionarioPersona -> {

                if (cuestionarioPersona.getRespuestas() == null) {
                    List<Integer> respuestas = new ArrayList<>();
                    for (int i = 0; i < this.biaPreguntaRepository.findAll().size(); i++) {
                        respuestas.add(0); // Si no tiene respuestas, se le asigna con una calificacion en 0 por defaulT
                    }
                    cuestionarioPersona.setRespuestas(respuestas);
                } else {
                    cuestionarioPersona.getRespuestas().add(0); // Agrego una respuesta con calificacion 0 al final debio al ingreso de la nueva pregunta
                }

                this.biaCuestionarioRepository.save(cuestionarioPersona);

            });
        });

        return nuevaPregunta;
    }

    @Override
    @Transactional
    public BIAPregunta actualizar(BIAPregunta pojo) throws DataAccessException {
        return this.biaPreguntaRepository.save(pojo);
    }

    @Override
    @Transactional(readOnly = true)
    public BIAPregunta buscaPorId(String identificador) throws NoEncontradoExcepcion {
        Optional<BIAPregunta> pregunta = this.biaPreguntaRepository.findByPregunta(identificador);
        if (!pregunta.isPresent()) {
            return pregunta.get();
        }
        throw new NoEncontradoExcepcion("mensaje", "No se ha encontrado a ".concat(identificador));
    }

    @Override
    @Transactional
    public void eliminarDocumento(String identificador) throws EliminacionException {
        this.biaPreguntaRepository.findById(identificador).ifPresent(this.biaPreguntaRepository::delete);
    }

    @Override
    @Transactional
    public BIAPregunta actualizarPreguntaPosicion(BIAPregunta pojo, Integer posicion) {

        BIAPregunta nuevaPregunta = this.biaPreguntaRepository.save(pojo);

        this.personaRepository.findPersonaByRole("DEPARTAMENTO").forEach(persona -> {
            this.biaCuestionarioRepository.findByUsuario(persona.getUsuario().getNombreUsuario()).ifPresent(cuestionarioPersona -> {

                if (cuestionarioPersona.getRespuestas() == null) {
                    List<Integer> respuestas = new ArrayList<>();
                    for (int i = 0; i < this.biaPreguntaRepository.findAll().size(); i++) {
                        respuestas.add(0); // Si no tiene respuestas, se le asigna con una calificacion en 0 por default
                    }
                    cuestionarioPersona.setRespuestas(respuestas);
                } else {
                    cuestionarioPersona.getRespuestas().set(posicion - 1, 0); // La pregunta actualizada ahora tiene una calificacion de 0 para todos
                }
                this.biaCuestionarioRepository.save(cuestionarioPersona);
            });
        });

        return nuevaPregunta;
    }

    @Override
    @Transactional
    public void eliminarPreguntaPosicion(String identificador, Integer posicion) {
        this.biaPreguntaRepository.findById(identificador).ifPresent(biaPregunta -> {

            this.biaPreguntaRepository.delete(biaPregunta);

            this.personaRepository.findPersonaByRole("DEPARTAMENTO").forEach(persona -> {
                this.biaCuestionarioRepository.findByUsuario(persona.getUsuario().getNombreUsuario()).ifPresent(cuestionarioPersona -> {
                    if (cuestionarioPersona.getRespuestas() == null) {
                        List<Integer> respuestas = new ArrayList<>();
                        for (int i = 0; i < this.biaPreguntaRepository.findAll().size(); i++) {
                            respuestas.add(0); // Si no tiene respuestas, se le asigna con una calificacion en 0 por default y ya no se toma la que se elimina
                        }
                        cuestionarioPersona.setRespuestas(respuestas);
                    } else {
                        cuestionarioPersona.getRespuestas().remove(posicion - 1); // La pregunta actualizada ahora tiene una calificacion de 0 para todos
                    }
                    this.biaCuestionarioRepository.save(cuestionarioPersona);
                });
            });

        });
    }
}
