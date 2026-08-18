package com.api.apidos.pojo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data

public class CancelarRequest {
      @NotNull(message = "El id es obligatorio")
    private Long id;

    @NotBlank(message = "La referencia es obligatoria")
    private String referencia;

    @NotBlank(message = "El estatus es obligatorio")
    @Pattern(
        regexp = "(?i)^cancelar$",
        message = "El estatus únicamente puede ser cancelar"
    )
    private String estatus;
}
