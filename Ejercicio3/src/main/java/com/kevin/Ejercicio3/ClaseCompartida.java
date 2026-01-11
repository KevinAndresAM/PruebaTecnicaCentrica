package com.kevin.Ejercicio3;


import java.math.BigDecimal;

public class ClaseCompartida {

    private BigDecimal balance;

    public ClaseCompartida(BigDecimal saldoInicial) {
        this.balance = saldoInicial;
    }

    public synchronized void retiroDecuenta(BigDecimal retiro) throws InterruptedException {
        String threadName = Thread.currentThread().getName();

        if (balance.compareTo(retiro) >= 0) {
            System.out.println(threadName + " entrando. Saldo actual: " + balance);

            balance = balance.subtract(retiro);

            System.out.println(threadName + " retiró " + retiro + ". Saldo nuevo: " + balance);
        }
    }

    public synchronized BigDecimal getBalance() {
        return balance;
    }
}