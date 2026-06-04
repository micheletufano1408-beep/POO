package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnessioneDatabase {

	private static ConnessioneDatabase instance;
	private Connection connection;

	private final String url = "jdbc:postgresql://localhost:5432/casa_discografica";
	private final String username = "postgres";

	private final String password = "Michele-1408&";

	private ConnessioneDatabase() throws SQLException {
		try {
			this.connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException ex) {
			System.out.println("❌ Errore durante la creazione della connessione al database.");
			throw ex;
		}
	}


	public static ConnessioneDatabase getInstance() throws SQLException {
		if (instance == null || instance.getConnection().isClosed()) {
			instance = new ConnessioneDatabase();
		}
		return instance;
	}


	public Connection getConnection() {
		return connection;
	}


	public static void main(String[] args) {
		try {
			Connection conn = ConnessioneDatabase.getInstance().getConnection();
			if (conn != null && !conn.isClosed()) {
				System.out.println("✅Connessione al database 'casa_discografica' riuscita con successo!");
			}
		} catch (SQLException e) {
			System.out.println("❌ Errore di connessione. Controlla che PostgreSQL sia acceso e che la password sia corretta.");
			System.out.println("Dettaglio errore: " + e.getMessage());
		}
	}
}