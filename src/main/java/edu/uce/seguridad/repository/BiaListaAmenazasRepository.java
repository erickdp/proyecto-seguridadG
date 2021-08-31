package edu.uce.seguridad.repository;

import edu.uce.seguridad.model.BiaListaAmenazas;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BiaListaAmenazasRepository extends MongoRepository<BiaListaAmenazas, String> {

    BiaListaAmenazas findByUsuario(String usuario);

}
