package com.api.apidos.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apidos.Services.ApiDosService;
import com.api.apidos.pojo.VentaRqs;
import com.api.apidos.pojo.VentaRsp;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api-dos")
@RequiredArgsConstructor
public class ApiDosController {
    
    private final ApiDosService apiDosService;
    @PostMapping("/procesaVenta")
    public ResponseEntity<VentaRsp> procesarVenta(@RequestBody VentaRqs ventaRqs) {
         return apiDosService.procesarVenta(ventaRqs);
    }
    
  
}
