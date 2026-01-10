package main;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class TransactionService {
	private static final BigDecimal MONTO_CIEN = new BigDecimal(100);

	public List<Map.Entry<String, BigDecimal>> getTop3Users(List<String> listCsv) {
		return listCsv.stream().map(this::validateLine).filter(Optional::isPresent).map(Optional::get)
				.filter(transaction -> transaction.monto().compareTo(MONTO_CIEN) > 0)
				.collect(Collectors.groupingBy(TransactionRecord::usuarioId,
						Collectors.reducing(BigDecimal.ZERO, TransactionRecord::monto, BigDecimal::add)))
				.entrySet().stream().sorted(Map.Entry.<String, BigDecimal>comparingByValue().reversed()).limit(3)
				.toList();
	}

	private Optional<TransactionRecord> validateLine(String line) {
		try {
			String[] fields = line.split(",");
			return Optional.of(new TransactionRecord(fields[0].trim(), fields[1].trim(),
					new BigDecimal(fields[2].trim()), fields[3].trim(), fields[4].trim()));
		} catch (Exception e) {
			System.err.println("Línea corrupta saltada: " + line + " | Error: " + e.getMessage());
			return Optional.empty();
		}
	}
}
