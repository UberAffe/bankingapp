package bankingapp.daos;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BasicDAO {

	public abstract void insert(String ...args) throws SQLException;
	public abstract boolean update(String ...args) throws SQLException;
	public abstract void delete(String ...args) throws SQLException;
	public abstract ArrayList select(String ...args) throws SQLException;
}
