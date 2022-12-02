package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class AvventoreDAO implements DAO<Avventore> {
	private LinkedList<Avventore> avventori;
	private String query;
	private Statement statement;
	private ResultSet table;
	private Connection connection;
	
	public AvventoreDAO(Connection connection) throws SQLException {
		avventori = new LinkedList<Avventore>();
		query = "SELECT* FROM Avventore";
		statement = connection.createStatement();
		table = statement.executeQuery(query);
		while(table.next()) {
			avventori.add(new Avventore(table.getString("codCartaIdentità"),
							   			table.getString("nome"),
							   			table.getString("cognome"),
							   			table.getString("n°telefono")));
		}
		this.connection = connection;
	}
	
	public boolean exists(Avventore avventore) {
		return avventori.contains(avventore);
	}
	
	public boolean notExists(Avventore avventore) {
		return !exists(avventore);
	}

	public Avventore get(String codCartaIdentità) {
		for (Avventore avventore : avventori)
			if(avventore.getCodCartaIdentità().equals(codCartaIdentità))
				return avventore;
		return null;
	}

	@Override
	public List<Avventore> getAll() {
		return avventori;
	}

	@Override
	public void insert(Avventore element) throws SQLException {
		query = "INSERT INTO Avventore(codCartaIdentità, nome, cognome, n°telefono) VALUES (?, ?, ?, ?)";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, element.getCodCartaIdentità());
		preparedStatement.setString(2, element.getNome());
		preparedStatement.setString(3, element.getCognome());
		preparedStatement.setString(4, element.getNumeroDiTelefono());
		preparedStatement.execute();
		avventori.add(element);
	}
	
	public void insert(List<Avventore> elements) throws SQLException {
		query = "INSERT INTO Avventore(codCartaIdentità, nome, cognome, n°telefono) VALUES ";
		boolean thisIsTheFirstElement = true;
		for(Avventore element : elements) {
			if(notExists(element)) {
				query = query + (thisIsTheFirstElement ? "" : ", ") + 
						"('" + element.getCodCartaIdentità() + "'" +
						", '" + element.getNome() + "'" +
						", '" + element.getCognome() + "'" +
						", '" + element.getNumeroDiTelefono() + "'" + ")";
				thisIsTheFirstElement = false;
				avventori.add(element);
			}
		}
		statement.execute(query);
	}

	@Override
	public void delete(Avventore element) throws SQLException {
		query = "DELETE FROM Avventore WHERE codCartaIdentità = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, element.getCodCartaIdentità());
		preparedStatement.executeUpdate();
		avventori.remove(element);
	}

}
