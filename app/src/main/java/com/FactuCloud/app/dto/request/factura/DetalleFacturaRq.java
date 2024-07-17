package com.FactuCloud.app.dto.request.factura;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleFacturaRq {

    private String descripcion;
    private Integer cantidad;
    private Double precioUnitario;
    //private Double totalLinea;
}
