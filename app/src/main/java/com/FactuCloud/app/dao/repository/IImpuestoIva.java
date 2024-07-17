package com.FactuCloud.app.dao.repository;

import com.FactuCloud.app.dao.model.ImpuestoIva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IImpuestoIva extends JpaRepository<ImpuestoIva, Integer> {
}
