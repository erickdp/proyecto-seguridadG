package edu.uce.seguridad.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BiaListaAmenazas {

    @Id
    private String _id;
    @Indexed(unique = true)
    private String usuario;
    private HashMap<String, List<BiaAmenaza>> lista;

}
