package com.FactuCloud.app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UsuarioRs {

    private String nombre;
    private String cif;
    private String direccion;
    private String ciudad;
    private String pais;
    private String email;
    private String telefono;
}
