package edu.uce.seguridad.repository;

import edu.uce.seguridad.model.ListaContactoExterno;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ListaContactoExternoRepository extends MongoRepository<ListaContactoExterno, String>{
    List<ListaContactoExterno> findByUserOrderByTipoContactoAsc(String user);

}
