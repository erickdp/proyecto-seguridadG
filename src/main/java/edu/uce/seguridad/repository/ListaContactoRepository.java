package edu.uce.seguridad.repository;

import edu.uce.seguridad.model.ListaContacto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ListaContactoRepository extends MongoRepository<ListaContacto, String> {

    List<ListaContacto> findByUser(String user);

}
