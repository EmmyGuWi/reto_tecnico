package com.api.apiuno.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.api.apiuno.Pojo.VentaObj;
import com.api.apiuno.Pojo.VentaRsp;
import com.api.apiuno.Services.VentaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import  com.api.apiuno.Client.ApiDosClient;

@Controller
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class VentaController {

 private  final VentaService ventaService;

 @PostMapping("/venta")
 public ResponseEntity<VentaRsp> procesarVenta(
            @Valid @RequestBody VentaObj venta) {

        return ResponseEntity.ok(
            ventaService.procesarVenta(venta)
        );
    }

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> manejarValidaciones(
            MethodArgumentNotValidException ex) {

        return ResponseEntity
                .badRequest()
                .body("Datos inválidos");
    }
}

    
}
