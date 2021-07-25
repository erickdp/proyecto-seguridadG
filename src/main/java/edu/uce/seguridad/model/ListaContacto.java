package edu.uce.seguridad.model;

import com.mongodb.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Unwrapped;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListaContacto {

    @Id
    private String _id;
    private String user;
    private String tipoContacto;
    private String nombre;
    private String telefono;
    private String correo;
    private String servicio;

}
