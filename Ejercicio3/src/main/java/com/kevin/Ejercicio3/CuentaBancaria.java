package com.kevin.Ejercicio3;


import java.math.BigDecimal;

public class CuentaBancaria {

    public static void main(String[] args) {

        ClaseCompartida cuentaCompartida = new ClaseCompartida(new BigDecimal("1000"));

        for (int i = 1; i <= 10; i++) {
            Thread t = new Thread(() -> {
                try {
                    cuentaCompartida.retiroDecuenta(new BigDecimal("100"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "RETIRO");

            t.start();
        }
    }
}