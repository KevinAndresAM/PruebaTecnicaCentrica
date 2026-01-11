package com.kevin.Ejercicio2.controller;

import javax.naming.ServiceUnavailableException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kevin.Ejercicio2.dto.FacturaRequest;
import com.kevin.Ejercicio2.service.FacturaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/facturas")
public class FacturaController {

	@Autowired
	private FacturaService facturaService;

	@PostMapping
	public ResponseEntity<String> crearFactura(@Valid @RequestBody FacturaRequest factura) {
		try {
			facturaService.procesarFactura(factura);
			return ResponseEntity.ok("Factura procesada correctamente");
		} catch (ServiceUnavailableException e) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
					.body("Error externo, factura guardada como PENDIENTE");
		}
	}
}