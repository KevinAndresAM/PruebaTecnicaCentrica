package com.kevin.Ejercicio2.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Positive;

public record FacturaRequest(Long id, @Positive(message = "El total debe ser un número positivo") BigDecimal total,
		String estado) {

	public FacturaRequest withEstado(String nuevoEstado) {

		return new FacturaRequest(this.id, this.total, nuevoEstado);

	}
}
