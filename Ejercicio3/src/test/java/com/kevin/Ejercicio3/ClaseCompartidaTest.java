package com.kevin.Ejercicio3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ClaseCompartidaTest {

    @Test
    public void testRetirosSimultaneosExitosos() throws InterruptedException {

        BigDecimal saldoInicial = new BigDecimal("1000");
        ClaseCompartida cuenta = new ClaseCompartida(saldoInicial);

        int numeroDeHilos = 20; 
        BigDecimal montoRetiro = new BigDecimal("100");

        ExecutorService executor = Executors.newFixedThreadPool(numeroDeHilos);
        CountDownLatch startGate = new CountDownLatch(1);
        CountDownLatch endGate = new CountDownLatch(numeroDeHilos);

        for (int i = 0; i < numeroDeHilos; i++) {
            executor.submit(() -> {
                try {
                    startGate.await();
                    cuenta.retiroDecuenta(montoRetiro);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    endGate.countDown();
                }
            });
        }

        startGate.countDown(); 

        boolean completado = endGate.await(10, TimeUnit.SECONDS);
        executor.shutdown();


        assertEquals(BigDecimal.ZERO, cuenta.getBalance());
    }
}