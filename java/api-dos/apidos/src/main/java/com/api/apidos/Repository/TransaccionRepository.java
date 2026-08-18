package com.api.apidos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.apidos.Entities.Transaccion;


public interface TransaccionRepository  extends JpaRepository<Transaccion, Long> {
 @Modifying
    @Query("""
        UPDATE Transaccion t
        SET t.estatus = 'CANCELADA'
        WHERE t.id = :id
          AND t.referencia = :referencia
          AND t.estatus = 'APROBADA'
    """)
    int cancelarTransaccion(
        @Param("id") Long id,
        @Param("referencia") String referencia
    );
        
}
