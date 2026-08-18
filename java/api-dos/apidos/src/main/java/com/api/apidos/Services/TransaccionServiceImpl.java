package com.api.apidos.Services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.apidos.Repository.TransaccionRepository;
import com.api.apidos.pojo.CancelarRequest;

@Service
public class TransaccionServiceImpl implements TransaccionService {
    private final TransaccionRepository repository;

    public TransaccionServiceImpl(
            TransaccionRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public void cancelar(CancelarRequest request) {

        int registrosActualizados =
                repository.cancelarTransaccion(
                    request.getId(),
                    request.getReferencia()
                );

        if (registrosActualizados == 0) {
            throw new RuntimeException(
                "No se encontró una transacción aprobada " +
                "con ese id y referencia"
            );
        }
    }

}
