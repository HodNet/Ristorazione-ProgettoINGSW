package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class TavoloDAO implements DAO<Tavolo> {
	private LinkedList<Tavolo> tavoli;
	private String query;
	private Statement statement;
	private ResultSet table;
	private Connection connection;
	
	public TavoloDAO(Connection connection) throws SQLException {
		tavoli = new LinkedList<Tavolo>();
		query = "SELECT* FROM Tavolo";
		statement = connection.createStatement();
		table = statement.executeQuery(query);
		while(table.next()) {
			tavoli.add(new Tavolo(table.getString("codT"),
							   table.getInt("n°maxAvventori"),
							   table.getString("codS")));
		}
		this.connection = connection;
	}
	
	public Tavolo get(String ID) {
		for (Tavolo tavolo : tavoli)
			if(tavolo.getID().equals(ID))
				return tavolo;
		return null;
	}
	
	public List<Tavolo> getTavoliOf(Ristorante ristorante) throws SQLException {
		LinkedList<Tavolo> tavoliSelected = new LinkedList<Tavolo>();
		query = "SELECT codT, n°maxAvventori, codS FROM Tavolo NATURAL JOIN Sala NATURAL JOIN Ristorante WHERE codR=" + ristorante.getID();
		statement = connection.createStatement();
		table = statement.executeQuery(query);
		while(table.next()) {
			tavoliSelected.add(new Tavolo(table.getString("codT"),
					   					table.getInt("n°maxAvventori"),
					   					table.getString("codS")));
		}
		return tavoliSelected;
	}

	@Override
	public List<Tavolo> getAll() {
		return tavoli;
	}

	@Override
	public void insert(Tavolo element) throws SQLException {
		query = "INSERT INTO Tavolo(n°maxAvventori, codS) VALUES (?, ?)";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, element.getNumeroMassimoDiAvventori());
		preparedStatement.setInt(2, Integer.valueOf(element.getSalaID()));
		preparedStatement.execute();
		tavoli.add(element);
	}

	@Override
	public void delete(Tavolo element) throws SQLException {
		query = "DELETE FROM Tavolo WHERE codT = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, Integer.valueOf(element.getID()));
		preparedStatement.executeUpdate();
		tavoli.remove(element);
	}

}
