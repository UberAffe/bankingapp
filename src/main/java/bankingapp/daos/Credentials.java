package bankingapp.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Credentials {

	protected static final String[] credentials= {
			"jdbc:postgresql://localhost/BankingApp",
			"postgres",
			"Eleven28Ten"
	};
	
	protected static final Connection getConnection() throws SQLException {
		Connection conn = DriverManager.getConnection(credentials[0], credentials[1], credentials[2]);
		return conn;
		}
}
