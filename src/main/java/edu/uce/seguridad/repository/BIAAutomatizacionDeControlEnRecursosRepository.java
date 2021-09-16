package edu.uce.seguridad.repository;

import edu.uce.seguridad.model.BIAAutomatizacionDeControlEnRecursos;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface BIAAutomatizacionDeControlEnRecursosRepository extends MongoRepository<BIAAutomatizacionDeControlEnRecursos, String > {
     List<BIAAutomatizacionDeControlEnRecursos> findByUserOrderByInmueble(String user);
}
