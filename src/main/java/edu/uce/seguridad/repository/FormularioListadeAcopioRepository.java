package edu.uce.seguridad.repository;

import edu.uce.seguridad.model.FormularioListadeAcopio;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FormularioListadeAcopioRepository extends MongoRepository<FormularioListadeAcopio, String> {
    List<FormularioListadeAcopio> findByUsuario(String usuario);
}
