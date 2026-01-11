package com.kevin.Ejercicio2.service;

import org.springframework.stereotype.Service;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

@Service
public class HaciendaService {

	@Retryable(retryFor = RuntimeException.class, maxAttempts = 3, backoff = @Backoff(delay = 1000))
	public void llamarHacienda() {
		try {
			Thread.sleep(500);
			if (Math.random() < 0.2) {
				throw new RuntimeException("Error en comunicación con Hacienda");
			}
			System.out.println("Comunicación exitosa con Hacienda");
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
