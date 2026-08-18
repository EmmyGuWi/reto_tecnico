package com.api.apiuno.Pojo;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class VentaObj {

    @NotBlank
    @Size(min = 3, max = 20)
    @Pattern(
        regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ]+$",
        message = "La operación solo debe contener letras"
    )
    private String operacion;

    @NotNull(message = "El importe es obligatorio")
    @DecimalMin(
        value = "0.01",
        message = "El importe debe ser mayor a cero"
    )
    @Digits(
        integer = 10,
        fraction = 2,
        message = "El importe debe tener máximo dos decimales"
    )
    private BigDecimal importe;

  
    @Size(min = 3, max = 20)
    @Pattern(
        regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ]+$",
        message = "La operación solo debe contener letras"
    )
    private String cliente;
    
    private String secreto;
}
