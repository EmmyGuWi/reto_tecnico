package com.api.apiuno.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apiuno.Client.ApiDosClient;
import com.api.apiuno.Pojo.CancelarRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/transacciones")
public class TransaccionController {

    private final ApiDosClient apiDosClient;

    public TransaccionController(ApiDosClient apiDosClient) {
        this.apiDosClient = apiDosClient;
    }

    @PatchMapping("/cancelar")
    public ResponseEntity<Void> cancelar(
            @Valid @RequestBody CancelarRequest request) {

        apiDosClient.cancelar(request);

        return ResponseEntity.noContent().build();
    }
}
