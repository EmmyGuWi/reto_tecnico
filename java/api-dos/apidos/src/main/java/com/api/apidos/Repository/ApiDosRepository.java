package com.api.apidos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.apidos.Entities.venta;

public interface ApiDosRepository extends JpaRepository<venta, Long> {

}
