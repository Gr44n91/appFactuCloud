package com.FactuCloud.app.services;

import com.FactuCloud.app.dao.model.Factura;
import com.FactuCloud.app.dto.request.factura.FacturaRq;

public interface FacturaService {

    Factura crearFactura(FacturaRq request);
}
