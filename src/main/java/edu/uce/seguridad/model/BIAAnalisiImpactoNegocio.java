package edu.uce.seguridad.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
public class BIAAnalisiImpactoNegocio {
    @Id
    private String _id;
    @Indexed(unique = true)
    private String usuario;
    private List<BIAAnalisisImpactoNegocioDetalle> detalle;
}
