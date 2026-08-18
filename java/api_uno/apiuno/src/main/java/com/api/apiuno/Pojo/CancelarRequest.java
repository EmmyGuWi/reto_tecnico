package com.api.apiuno.Pojo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CancelarRequest {

    @NotNull(message = "El id es obligatorio")
    private Long id;

    @NotBlank(message = "La referencia es obligatoria")
    @Size(
        max = 50,
        message = "La referencia no puede superar los 50 caracteres"
    )
    @Pattern(
        regexp = "^[A-Za-z0-9-]+$",
        message = "La referencia contiene caracteres no permitidos"
    )
    private String referencia;

    @NotBlank(message = "El estatus es obligatorio")
    @Pattern(
        regexp = "(?i)^cancelar$",
        message = "El estatus únicamente puede ser cancelar"
    )
    private String estatus;
}
