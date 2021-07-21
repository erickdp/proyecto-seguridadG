package edu.uce.seguridad.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    private String _id;
    @Indexed(unique = true)
    private String usuario;
    private String contrasena;
    private String role;
}
