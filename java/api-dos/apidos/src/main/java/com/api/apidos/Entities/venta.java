package com.api.apidos.Entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "venta") 
@Getter
@Setter
@NoArgsConstructor

public class venta {

    @Id 
    @Column(name = "PK")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pk; 
    @Column(name = "operacion", length = 100)
    private String operacion;
    @Column(name = "importe", precision = 12, scale = 4)
    private BigDecimal importe;
    @Column(name = "cliente", length = 100)
    private String cliente;
    @Column(name = "referencia", length = 100)
    private String referencia;
    @Column(name = "estatus", length = 50)
    private String estatus;
    @Column(name = "secreto", length = 100)
    private String secreto;

}
