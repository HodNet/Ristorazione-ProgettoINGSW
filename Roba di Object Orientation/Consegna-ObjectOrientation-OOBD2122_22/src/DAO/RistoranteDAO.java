package DAO;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class RistoranteDAO implements DAO<Ristorante> {

	private LinkedList<Ristorante> ristoranti;
	private String query;
	private Statement statement;
	private ResultSet table;
	private Connection connection;

	public RistoranteDAO(Connection connection) throws SQLException {
		ristoranti = new LinkedList<Ristorante>();
		query = "SELECT* FROM Ristorante";
		statement = connection.createStatement();
		table = statement.executeQuery(query);
		while(table.next()) {
			ristoranti.add(new Ristorante((table.getString("codR")),
										  table.getString("nome"),
										  table.getString("città"),
										  table.getString("indirizzo"),
										  table.getString("n°civico"),
										  table.getByte("n°sale")));
		}
		this.connection = connection;
	}
	
	public Ristorante get(String ID) {
		for (Ristorante x : ristoranti)
			if(x.getID().equals(ID))
				return x;
		return null;
	}
	
	@Override
	public List<Ristorante> getAll() {
		return ristoranti;
	}
	
	@Override
	public void insert(Ristorante element) throws SQLException {
		query = "INSERT INTO Ristorante(nome, città, indirizzo, n°civico) VALUES (?, ?, ?, ?)";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, element.getNome());
		preparedStatement.setString(2, element.getCittà());
		preparedStatement.setString(3, element.getIndirizzo());
		preparedStatement.setString(4, element.getNumeroCivico());
		preparedStatement.execute();
		ristoranti.add(element);
	}
 
	@Override
    public void delete(Ristorante element) throws SQLException {
		query = "DELETE FROM Ristorante WHERE codR = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, Integer.valueOf(element.getID()));
		preparedStatement.executeUpdate();
		ristoranti.remove(element);
	}
	
	public int getNumeroRistoranti() {
		return ristoranti.size();
	}
}
