package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class SalaDAO implements DAO<Sala> {
	private LinkedList<Sala> sale;
	private String query;
	private Statement statement;
	private ResultSet table;
	private Connection connection;
	
	public SalaDAO(Connection connection) throws SQLException {
		sale = new LinkedList<Sala>();
		query = "SELECT* FROM Sala";
		statement = connection.createStatement();
		table = statement.executeQuery(query);
		while(table.next()) {
			sale.add(new Sala(table.getString("codS"),
							   table.getInt("n°tavoli"),
							   table.getString("codR")));
		}
		this.connection = connection;
	}
	
	public Sala get(String ID) {
		for (Sala sala : sale)
			if(sala.getID().equals(ID))
				return sala;
		return null;
	}
	
	@Override
	public List<Sala> getAll() {
		return sale;
	}
	
	public List<Sala> getAllOf(Ristorante ristorante) {
		LinkedList<Sala> ret = new LinkedList<Sala>();
		for(Sala x : sale) {
			if(x.getRistoranteID().equals(ristorante.getID())) {
				ret.add(x);
			}
		}
		return ret;
	}

	@Override
	public void insert(Sala element) throws SQLException {
		query = "INSERT INTO Sala(codR) VALUES (?)";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, Integer.valueOf(element.getRistoranteID()));
		preparedStatement.execute();
		sale.add(element);
	}

	@Override
	public void delete(Sala element) throws SQLException {
		query = "DELETE FROM Sala WHERE codS = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, Integer.valueOf(element.getID()));
		preparedStatement.executeUpdate();
		sale.remove(element);
	}
	
}
