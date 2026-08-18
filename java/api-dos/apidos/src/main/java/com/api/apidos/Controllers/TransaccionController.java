package com.api.apidos.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apidos.Services.TransaccionService;
import com.api.apidos.pojo.CancelarRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/transacciones")
public class TransaccionController {
  private final TransaccionService service;

    public TransaccionController(
            TransaccionService service) {
        this.service = service;
    }

    @PatchMapping("/cancelar")
    public ResponseEntity<Void> cancelar(
            @Valid @RequestBody CancelarRequest request) {

        service.cancelar(request);

        return ResponseEntity.noContent().build();
    }
}
