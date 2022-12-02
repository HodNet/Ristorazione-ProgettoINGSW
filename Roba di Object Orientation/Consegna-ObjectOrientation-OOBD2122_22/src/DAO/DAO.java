package DAO;
import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
	//public T get(String ID);
    
    public List<T> getAll();
    
    public void insert (T element) throws SQLException;
    
    public void delete(T element) throws SQLException;
}
