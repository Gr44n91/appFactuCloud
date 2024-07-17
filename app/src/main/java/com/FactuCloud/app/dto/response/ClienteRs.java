package com.FactuCloud.app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteRs {

    private String nombre;
    private String cifDni;
    private String direccion;
    private String ciudad;
    private String email;
    private String telefono;
}
