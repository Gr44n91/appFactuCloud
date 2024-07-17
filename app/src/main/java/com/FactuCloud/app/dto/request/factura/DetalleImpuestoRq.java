package com.FactuCloud.app.dto.request.factura;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleImpuestoRq {

    @NotBlank
    private String tipoImpuesto;
    @Positive
    private Double monto;
}
