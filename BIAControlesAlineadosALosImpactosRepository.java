package edu.uce.seguridad.repository;

import edu.uce.seguridad.model.BIAControlesAlineadosALosImpactos;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface BIAControlesAlineadosALosImpactosRepository extends MongoRepository<BIAControlesAlineadosALosImpactos, String >{
    
     List<BIAControlesAlineadosALosImpactos> findByUserOrderByInmueble(String user);
}
