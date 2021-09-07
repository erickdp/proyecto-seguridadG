package edu.uce.seguridad.repository;

import edu.uce.seguridad.model.BIAPeriocidadDeAccionesDeControlEnRecursos;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface BIAPeriocidadDeAccionesDeControlEnRecursosRepository extends MongoRepository<BIAPeriocidadDeAccionesDeControlEnRecursos, String > {
         List<BIAPeriocidadDeAccionesDeControlEnRecursos> findByUserOrderByInmueble(String user);
}
