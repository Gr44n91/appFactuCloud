package com.FactuCloud.app.dao.repository;

import com.FactuCloud.app.dao.model.DetalleImpuesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDetalleImpuestoRepository extends JpaRepository<DetalleImpuesto, Integer> {
}
