package edu.uce.seguridad.repository;

import edu.uce.seguridad.model.HojaDeRevisionDeGerencia;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HojaDeRevisionDeGerenciaRepository extends MongoRepository<HojaDeRevisionDeGerencia, String>{
    
    List<HojaDeRevisionDeGerencia> findByUserOrderByAsuntoARevisaryVerificar(String user);
}
