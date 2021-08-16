package edu.uce.seguridad.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.List;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class FormularioListadeAcopio {
    @Id
    private String id;
    private String usuario;
    private HashMap<String, List<CategoriaFormularioListAcop>> categoria;
}
