package edu.uce.seguridad.repository;

import edu.uce.seguridad.model.BIAEficenciaBasadaEnControlesDeRecursos;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BIAEficenciaBasadaEnControlesDeRecursosRepository extends MongoRepository<BIAEficenciaBasadaEnControlesDeRecursos, String > {
         List<BIAEficenciaBasadaEnControlesDeRecursos> findByUserOrderByInmueble(String user);
}
