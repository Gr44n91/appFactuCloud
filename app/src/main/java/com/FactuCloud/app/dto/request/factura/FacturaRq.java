package com.FactuCloud.app.dto.request.factura;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FacturaRq {

    private Integer clienteId;
    private Integer usuarioId;
    private Integer ejercicio;
    private Date fecha;
    private List<DetalleFacturaRq> detallesFactura; // <>
    private List<DetalleImpuestoRq> detallesImpuestos; // <>
}
