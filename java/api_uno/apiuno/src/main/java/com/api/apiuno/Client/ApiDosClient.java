package com.api.apiuno.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.api.apiuno.Config.FeignConfig;
import com.api.apiuno.Pojo.CancelarRequest;
import com.api.apiuno.Pojo.VentaObj;
import com.api.apiuno.Pojo.VentaRsp;
import org.springframework.web.bind.annotation.PatchMapping;
@FeignClient(
    name = "procesamiento",
    url = "${api.procesamiento.url}",
    configuration = FeignConfig.class
)
public interface ApiDosClient {
    @PostMapping("/procesaVenta")
    VentaRsp procesar(@RequestBody VentaObj venta);

      @PatchMapping("/transacciones/cancelar")
    void cancelar(
        @RequestBody CancelarRequest request
    );
}
