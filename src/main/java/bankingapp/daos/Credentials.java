package bankingapp.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Credentials {

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:postgresql://localhost/BankingApp", "postgres", "Eleven28Ten");
		}
}
