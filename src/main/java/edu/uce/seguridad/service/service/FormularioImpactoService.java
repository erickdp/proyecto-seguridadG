package edu.uce.seguridad.service.service;

import edu.uce.seguridad.model.FormularioComparativoImpacto;

import java.util.List;

public interface FormularioImpactoService extends BaseService<FormularioComparativoImpacto, String> {
    List<FormularioComparativoImpacto> buscarFormularioImpPorUsua(String usuario);
}
