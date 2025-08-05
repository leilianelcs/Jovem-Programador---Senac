package sistemaTaxi.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    
	private static final String URL = "jdbc:postgresql://localhost:5432/db_sistemaTaxi";
	private static final String USER = "postgres";
	private static final String PASSWORD = "postgres";
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}

}
