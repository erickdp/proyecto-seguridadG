package edu.uce.seguridad.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BiaValoracionGeneral {

    @Id
    private String _id;
    @Indexed(unique = true)
    private String usuario;
    private List<DataAmenazas> amenazas;

}
