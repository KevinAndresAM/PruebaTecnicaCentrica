package main;

import java.util.List;

public class Ejercicio1 {

	public static void main(String[] args) {
		List<String> mockCsv = List.of(
	            "1, 2023-01-01, 150.00, USD, user_A",
	            "2, 2023-01-01, 50.00, USD, user_B",  // Menor a 100, se descarta
	            "3, error_data, invalid, USD, user_C", // Corrupta, se salta
	            "4, 2023-01-02, 200.00, USD, user_A",
	            "5, 2023-01-02, 500.00, USD, user_C",
	            "6, 2023-01-03, 300.00, USD, user_D",
	            "7, 2023-01-03, 120.00, USD, user_B"
	        );

	        TransactionService service = new TransactionService();
	        var topUsers = service.getTop3Users(mockCsv);

	        System.out.println("--- TOP 3 USUARIOS ---");
	        topUsers.forEach(entry -> 
	            System.out.println("Usuario: " + entry.getKey() + " | Total: $" + entry.getValue()));

	}

}
