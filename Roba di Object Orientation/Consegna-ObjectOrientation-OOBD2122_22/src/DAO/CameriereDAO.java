package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class CameriereDAO implements DAO<Cameriere> {
	private LinkedList<Cameriere> camerieri;
	private String query;
	private Statement statement;
	private ResultSet table;
	private Connection connection;
	
	public CameriereDAO(Connection connection) throws SQLException {
		camerieri = new LinkedList<Cameriere>();
		query = "SELECT* FROM Cameriere";
		statement = connection.createStatement();
		table = statement.executeQuery(query);
		while(table.next()) {
			camerieri.add(new Cameriere(table.getString("codCartaIdentità"),
							   			table.getString("nome"),
							   			table.getString("cognome"),
							   			table.getString("n°telefono"),
							   			table.getString("dataDiNascita"),
							   			table.getString("città"),
							   			table.getString("indirizzo"),
							   			table.getString("n°civico"),
							   			table.getString("sesso"),
							   			table.getString("codR")));
		}
		this.connection = connection;
	}
	
	public boolean exists(Cameriere cameriere) {
		return camerieri.contains(cameriere);
	}
	
	public boolean notExists(Cameriere cameriere) {
		return !exists(cameriere);
	}

	public Cameriere get(String codCartaIdentità) {
		for (Cameriere cameriere : camerieri) {
			if(cameriere.getCodCartaIdentità().equals(codCartaIdentità))
				return cameriere;
		}
		return null;
	}
	
	public List<Cameriere> getCamerieriOf(Ristorante ristorante) {
		LinkedList<Cameriere> camerieriSelected = new LinkedList<Cameriere>();
		for (Cameriere cameriere : camerieri) {
			if(cameriere.getRistoranteID().equals(ristorante.getID()))
				camerieriSelected.add(cameriere);
		}
		return camerieriSelected;
	}

	@Override
	public List<Cameriere> getAll() {
		return camerieri;
	}

	@Override
	public void insert(Cameriere element) throws SQLException {
		query = "INSERT INTO Cameriere(codCartaIdentità, nome, cognome, n°telefono, dataDiNascita, città, indirizzo, n°civico, sesso, codR) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, element.getCodCartaIdentità());
		preparedStatement.setString(2, element.getNome());
		preparedStatement.setString(3, element.getCognome());
		preparedStatement.setString(4, element.getNumeroDiTelefono());
		preparedStatement.setDate(5, Date.valueOf(element.getDataDiNascita()));
		preparedStatement.setString(6, element.getCittà());
		preparedStatement.setString(7, element.getIndirizzo());
		preparedStatement.setString(8, element.getNumeroCivico());
		preparedStatement.setString(9, element.getSesso());
		preparedStatement.setInt(10, Integer.valueOf(element.getRistoranteID()));
		preparedStatement.execute();
		camerieri.add(element);
	}

	@Override
	public void delete(Cameriere element) throws SQLException {
		query = "DELETE FROM Cameriere WHERE codCartaIdentità = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, element.getCodCartaIdentità());
		preparedStatement.executeUpdate();
		camerieri.remove(element);
	}

}
