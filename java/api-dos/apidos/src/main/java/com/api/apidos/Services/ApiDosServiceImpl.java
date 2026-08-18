package com.api.apidos.Services;

import java.security.SecureRandom;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apidos.Entities.venta;
import com.api.apidos.Repository.ApiDosRepository;
import com.api.apidos.pojo.VentaRqs;
import com.api.apidos.pojo.VentaRsp;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ApiDosServiceImpl implements ApiDosService {

    private final ApiDosRepository apiDosRepository;
  
    @Override
    public ResponseEntity<VentaRsp> procesarVenta(VentaRqs ventaRqs) {
        venta ventaEntity = new venta();
        ventaEntity.setOperacion(ventaRqs.getOperacion());
        ventaEntity.setImporte(ventaRqs.getImporte());
        ventaEntity.setCliente(ventaRqs.getCliente());
        ventaEntity.setReferencia(generarReferencia());
        ventaEntity.setEstatus("APROBADO");
        ventaEntity.setSecreto(ventaRqs.getSecreto());
        venta vtaRsp= apiDosRepository.save(ventaEntity);
        VentaRsp ventaRsp = new VentaRsp();
        ventaRsp.setId(vtaRsp.getPk());
        ventaRsp.setOperacion(vtaRsp.getOperacion());
        ventaRsp.setEstatus(vtaRsp.getEstatus());
        ventaRsp.setReferencia(vtaRsp.getReferencia());

    
          return ResponseEntity.ok(ventaRsp);
            
    }
    

    public String generarReferencia() {
        SecureRandom secureRandom = new SecureRandom();
        int referencia = 100_000 + secureRandom.nextInt(900_000);
        return String.valueOf(referencia);
    }
}
