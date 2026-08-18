package com.api.apidos.Services;

import org.springframework.http.ResponseEntity;

import com.api.apidos.Entities.venta;
import com.api.apidos.pojo.VentaRqs;
import com.api.apidos.pojo.VentaRsp;


public interface ApiDosService {
   public ResponseEntity<VentaRsp> procesarVenta(VentaRqs ventaRqs);
}
