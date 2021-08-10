package edu.uce.seguridad.repository;

import edu.uce.seguridad.model.FormularioComparativoImpacto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FormularioImpactoRepository extends MongoRepository<FormularioComparativoImpacto, String> {
    FormularioComparativoImpacto findFormularioComparativoImpactoByUser(String user);
}
