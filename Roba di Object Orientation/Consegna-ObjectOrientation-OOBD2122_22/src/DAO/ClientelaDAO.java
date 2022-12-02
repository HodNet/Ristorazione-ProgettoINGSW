package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class ClientelaDAO implements DAO<Clientela> {
	private LinkedList<Clientela> clientelaList;
	private LinkedList<String> dateList;
	private String query;
	private Statement statement;
	private ResultSet table;
	private Connection connection;
	
	public ClientelaDAO(Connection connection) throws SQLException {
		dateList = new LinkedList<String>();
		query = "SELECT DISTINCT dataDiArrivo FROM Clientela ORDER BY dataDiArrivo";
		statement = connection.createStatement();
		table = statement.executeQuery(query);
		while(table.next()) {
			dateList.add(new String(table.getString("dataDiArrivo")));
		}
		
		clientelaList = new LinkedList<Clientela>();
		query = "SELECT* FROM Clientela ORDER BY dataDiArrivo, codT";
		statement = connection.createStatement();
		table = statement.executeQuery(query);
		while(table.next()) {
			clientelaList.add(new Clientela(table.getString("codCartaIdentità"),
							   				table.getString("dataDiArrivo"),
							   				table.getString("codT")));
		}
		
		this.connection = connection;
	}
	
	public List<Clientela> getAvventoriOf(String dataDiArrivo, String tavoloID) {
		LinkedList<Clientela> clientelaSelected = new LinkedList<Clientela>();
		for (Clientela cliente : clientelaList) {
			if(cliente.getDataDiArrivo().equals(dataDiArrivo) && cliente.getTavoloID().equals(tavoloID))
				clientelaSelected.add(cliente);
		}
		return clientelaSelected;
	}
	
	public List<String> getAllTavoliOf(String data) {
		LinkedList<String> tavoliSelected = new LinkedList<String>();
		String tavoloPrecedente = null;
		for (Clientela cliente : clientelaList) {
			if(cliente.getDataDiArrivo().equals(data) && !cliente.getTavoloID().equals(tavoloPrecedente)) {
				tavoliSelected.add(cliente.getTavoloID());
				tavoloPrecedente = new String(tavoliSelected.getLast());
			}
		}
		return tavoliSelected;
	}
	
	public List<String> getAllTavoliOf(Ristorante ristorante, String data) throws SQLException {
		LinkedList<String> tavoliSelected = new LinkedList<String>();
		if(data!=null) {
			query = String.format("SELECT DISTINCT codT " +
					"FROM Clientela NATURAL JOIN Tavolo NATURAL JOIN Sala NATURAL JOIN Ristorante " +
					"WHERE codR = %s AND dataDiArrivo = '%s' " + 
					"ORDER BY codT", ristorante.getID(), data);
			statement = connection.createStatement();
			table = statement.executeQuery(query);
			while(table.next()) {
				tavoliSelected.add(new String(table.getString("codT")));
			}
		}
		return tavoliSelected;
	}
	
	public List<String> getAllDistinctDates() {
		return dateList;
	}
	
	public List<String> getAllDistinctDatesOf(Ristorante ristorante) throws SQLException {
		LinkedList<String> dateSelected = new LinkedList<String>();
		query = "SELECT DISTINCT dataDiArrivo " +
				"FROM Clientela NATURAL JOIN Tavolo NATURAL JOIN Sala NATURAL JOIN Ristorante " +
				"WHERE codR=" + ristorante.getID() + " " +
				"ORDER BY dataDiArrivo";
		statement = connection.createStatement();
		table = statement.executeQuery(query);
		while(table.next()) {
			dateSelected.add(new String(table.getString("dataDiArrivo")));
		}
		return dateSelected;
	}

	@Override
	public List<Clientela> getAll() {
		return clientelaList;
	}

	@Override
	public void insert(Clientela element) throws SQLException {
		query = "INSERT INTO Clientela(codCartaIdentità, dataDiArrivo, codT) VALUES (?, ?, ?)";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, element.getCodCartaIdentità());
		preparedStatement.setDate(2, Date.valueOf(element.getDataDiArrivo()));
		preparedStatement.setInt(3, Integer.valueOf(element.getTavoloID()));
		preparedStatement.execute();
		clientelaList.add(element);
	}
	
	public void insertTavolata(List<Avventore> avventori, String data, String tavoloID) throws SQLException {
		query = "INSERT INTO Clientela(codCartaIdentità, dataDiArrivo, codT) VALUES ";
		boolean isThisTheFirstElement = true;
		for(Avventore avventore : avventori) {
			query = query + (isThisTheFirstElement ? "" : ", ") + 
					"('" + avventore.getCodCartaIdentità() + "'" +
					", '" + data + "'" +
					", '" + tavoloID + "'" + ")";
			isThisTheFirstElement = false;
			clientelaList.add( new Clientela(avventore.getCodCartaIdentità(), data, tavoloID) );
		}
		statement.execute(query);
	}

	@Override
	public void delete(Clientela element) throws SQLException {
		query = "DELETE FROM Clientela WHERE codCartaIdentità=? AND dataDiArrivo=? AND codT=?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, element.getCodCartaIdentità());
		preparedStatement.setDate(2, Date.valueOf(element.getDataDiArrivo()));
		preparedStatement.setInt(3, Integer.valueOf(element.getTavoloID()));
		preparedStatement.executeUpdate();
		clientelaList.remove(element);
	}

}
