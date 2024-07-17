package com.FactuCloud.app.services.impl;

import com.FactuCloud.app.dao.model.*;
import com.FactuCloud.app.dao.repository.IClienteRepository;
import com.FactuCloud.app.dao.repository.IFacturaRepository;
import com.FactuCloud.app.dao.repository.IUsuarioRespository;
import com.FactuCloud.app.dto.request.factura.DetalleFacturaRq;
import com.FactuCloud.app.dto.request.factura.DetalleImpuestoRq;
import com.FactuCloud.app.dto.request.factura.FacturaRq;
import com.FactuCloud.app.exceptions.ResourceNotFoundException;
import com.FactuCloud.app.services.FacturaService;
import com.FactuCloud.app.util.FacturaUtil;

import java.util.List;
import java.util.stream.Collectors;

public class FacturaServiceImp implements FacturaService {

    private final IFacturaRepository facturaRepository;
    private final IUsuarioRespository usuarioRespository;
    private final IClienteRepository clienteRepository;

    public FacturaServiceImp(IFacturaRepository facturaRepository, IUsuarioRespository usuarioRespository, IClienteRepository clienteRepository) {
        this.facturaRepository = facturaRepository;
        this.usuarioRespository = usuarioRespository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Factura crearFactura(FacturaRq request) {

        Usuario usuario = usuarioRespository.findById(request.getUsuarioId()).orElseThrow(
                () -> new ResourceNotFoundException("Usuario no encontrado"));
        Cliente cliente = clienteRepository.findById(request.getUsuarioId()).orElseThrow(
                () -> new ResourceNotFoundException("Cliente no encontrado"));

        // Obtener el último número de factura para el usuario especificado
        String ultimoNumeroFactura = facturaRepository.findUltimoNumeroFacturaByUsuarioIdAndEjercicio(request.getUsuarioId(), request.getEjercicio());
        // Generar el siguiente número de factura
        String nuevoNumeroFactura = FacturaUtil.generarSiguienteNumeroFactura(ultimoNumeroFactura);

        //Creamos la factura
        Factura nuevaFactura = new Factura();
        // Asignar el número de factura a la nueva factura
        nuevaFactura.setNumeroFactura(nuevoNumeroFactura);
        nuevaFactura.setCliente(cliente);
        nuevaFactura.setUsuario(usuario);
        nuevaFactura.setFecha(request.getFecha());
        nuevaFactura.setDetallesFactura(request.getDetallesFactura().stream()
                .map(this::convertirADetalleFactura)
                .collect(Collectors.toList()));
        nuevaFactura.setTotal(calcularTotalFactura(nuevaFactura));

        //Calcular subtotal y total
        Double subtotal = calcularSubtotal(request.getDetallesFactura());
        Double totalImpuestos = calcularTotalImpuestos(request.getDetallesImpuestos());
        Double total = subtotal + totalImpuestos;


        // Guardar la nueva factura en la base de datos
        return facturaRepository.save(nuevaFactura);
    }

    //==================UTIL=====================00

    private DetalleFactura convertirADetalleFactura(DetalleFacturaRq detalleDTO) {

        DetalleFactura detalle = new DetalleFactura();
        detalle.setDescripcion(detalleDTO.getDescripcion());
        detalle.setCantidad(detalleDTO.getCantidad());
        detalle.setPrecioUnitario(detalleDTO.getPrecioUnitario());
        detalle.setTotalLinea(detalleDTO.getPrecioUnitario() * detalleDTO.getCantidad());

        return detalle;
    }
    //calcular subtotal con los detallesFactura
    private Double calcularSubtotal(List<DetalleFacturaRq> detallesFactura) {
        return detallesFactura.stream()
                .mapToDouble(detalle -> detalle.getPrecioUnitario() * detalle.getCantidad())
                .sum();
    }

    //Calcular total impuestos
    private Double calcularTotalImpuestos(List<DetalleImpuestoRq> detallesImpuestos) {
        return detallesImpuestos.stream()
                .mapToDouble(DetalleImpuestoRq::getMonto)
                .sum();
    }

    private double calcularTotalFactura(Factura factura) {
        Double subtotal = factura.getDetallesFactura().stream()
                .mapToDouble(DetalleFactura::getTotalLinea)
                .sum();

        Double totalImpuestos = factura.getDetallesImpuestos().stream()
                .mapToDouble(DetalleImpuesto::getMonto)
                .sum();

        return subtotal + (subtotal * totalImpuestos);
    }

}
