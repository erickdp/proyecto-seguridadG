package edu.uce.seguridad.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Organizacion {
    @Id
    private String _id;
    @Indexed(unique = true)
    private String organizacion;
    private List<String> departamentos;
    private String contacto;
}
