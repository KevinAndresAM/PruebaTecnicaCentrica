package main;

import java.math.BigDecimal;

public record TransactionRecord(String id, String fecha, BigDecimal monto, String moneda, String usuarioId) {
	
}
