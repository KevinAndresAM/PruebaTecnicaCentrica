package com.kevin.Ejercicio2.service;

import javax.naming.ServiceUnavailableException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kevin.Ejercicio2.dto.FacturaRequest;

@Service
public class FacturaService {

	@Autowired
	private HaciendaService haciendaService;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void guardarFactura(FacturaRequest factura, String estado) {
		try {
			Thread.sleep(100);
			factura.withEstado(estado);
			System.out.println("Factura guardada en DB con estado: " + estado);

		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	public void procesarFactura(FacturaRequest factura) throws ServiceUnavailableException {
		try {

			haciendaService.llamarHacienda();

			guardarFactura(factura, "PROCESADA");
		} catch (Exception e) {
			guardarFactura(factura, "PENDIENTE_ENVIO");
			throw new ServiceUnavailableException("Servicio externo no disponible");
		}
	}
}