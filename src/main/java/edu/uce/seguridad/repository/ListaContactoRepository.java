package edu.uce.seguridad.repository;

import edu.uce.seguridad.model.ListaContacto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ListaContactoRepository extends MongoRepository<ListaContacto, String> {
}
