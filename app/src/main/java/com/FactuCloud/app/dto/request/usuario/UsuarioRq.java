package com.FactuCloud.app.dto.request.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioRq {

    @NotBlank(message = "CIF Obligatorio y letra mayuscula")
    private String cifDni;
    private String ciudad;
    private String direccion;
    @NotBlank(message = "Email Obligatorio")
    @Email
    private String email;
    @NotBlank(message = "Nombre Obligatorio")
    private String nombre;
    private String pais;
    private String telefono;
}
