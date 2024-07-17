package com.FactuCloud.app.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteRq {

    @NotBlank
    private String nombre;
    @Pattern(regexp = "^(?:(?:[0-9]{8}[A-Za-z])|(?:[A-Za-z][0-9]{7}[A-Za-z0-9]))$"
    , message = "El DNI O CIF no es correcto")
    @NotBlank(message = "CIF Obligatorio y letra mayuscula")
    private String cifDni;
    @NotBlank(message = "La direccion es obligatoria")
    private String direccion;
    @NotBlank
    private String ciudad;
    @NotBlank(message = "Email Obligatorio")
    @Email
    private String email;
    private String telefono;
    @NotBlank(message = "El cif del usuario es obligatorio")
    private String cifUsuario;

}
