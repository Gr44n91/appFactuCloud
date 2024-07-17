package com.FactuCloud.app.util;

import com.FactuCloud.app.dao.repository.IFacturaRepository;

import java.text.DecimalFormat;

public class FacturaUtil {

    private final IFacturaRepository facturaRepository;

    public FacturaUtil(IFacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    static public String generarSiguienteNumeroFactura(String ultimoNumeroFactura) {
        int nuevoNumero = 1;

        if (ultimoNumeroFactura != null && !ultimoNumeroFactura.isEmpty()) {
            try {
                int ultimoNumero = Integer.parseInt(ultimoNumeroFactura);
                nuevoNumero = ultimoNumero + 1;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        // Formatear el número de factura con ceros a la izquierda si es necesario
        DecimalFormat formatoNumero = new DecimalFormat("000");
        return formatoNumero.format(nuevoNumero);
    }


}
