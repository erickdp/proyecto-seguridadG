package edu.uce.seguridad.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.HashMap;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RevisionContinua {
    @Id
    private String _id;
    private String usuario;
    private HashMap<String, List<FormularioRevision>> temas;
}
