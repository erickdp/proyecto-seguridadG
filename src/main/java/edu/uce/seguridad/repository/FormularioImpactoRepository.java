package edu.uce.seguridad.repository;

import edu.uce.seguridad.model.FormularioComparativoImpacto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FormularioImpactoRepository extends MongoRepository<FormularioComparativoImpacto, String> {
    List<FormularioComparativoImpacto> findFormularioComparativoImpactoByUser(String user);
}
