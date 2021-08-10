package edu.uce.seguridad.service.service;

import edu.uce.seguridad.model.FormularioComparativoImpacto;

public interface FormularioImpactoService extends BaseService<FormularioComparativoImpacto, String> {
    FormularioComparativoImpacto buscarFormularioImpPorUsua(String usuario);
}
