package com.api.apidos.pojo;

import java.math.BigDecimal;

import lombok.Data;
@Data
public class VentaRqs {
    private String operacion;
    private BigDecimal importe;
    private String cliente;
    private String secreto;
    private String referencia;
    private String estatus;
}
