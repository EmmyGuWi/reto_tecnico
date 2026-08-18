package com.api.apiuno.Services;
import org.springframework.stereotype.Service;

import com.api.apiuno.Client.ApiDosClient;
import com.api.apiuno.Pojo.VentaObj;
import com.api.apiuno.Pojo.VentaRsp;


@Service
public class VentaServiceImpl implements VentaService {
    
    private final  ApiDosClient apiDosClient;
      private final AesService aesService;

    public VentaServiceImpl(ApiDosClient apiDosClient, AesService aesService) {
        this.apiDosClient = apiDosClient;
        this.aesService = aesService;
    }

    @Override
    public VentaRsp procesarVenta(VentaObj venta) {
        // en el texto no se indica que hacer con el texto decifrado
           String secretoDescifrado =
            aesService.descifrar(venta.getSecreto());
            venta.setSecreto(secretoDescifrado);
        return apiDosClient.procesar(venta);
    }
    
}
