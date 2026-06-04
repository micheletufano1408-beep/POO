package implementazionePostgresDAO;

import database.ConnessioneDatabase;
import dao.EsempioDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class EsempioImplementazionePostgresDAO implements EsempioDAO {

	private Connection connection;

	public EsempioImplementazionePostgresDAO() {
		try {
			connection = ConnessioneDatabase.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void esempioQuery() {

	}

	@Override
	public void EsempioImplementazionePostgresDAO() {

	}
}
