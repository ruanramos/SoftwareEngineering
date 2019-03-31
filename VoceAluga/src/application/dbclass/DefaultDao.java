package application.dbclass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DefaultDao<T> {
	
	protected String tableName;
	protected String columnNames;
	protected String interrogationMarks;
	protected String formatForModifyingColumnsWhenUpdatingTableEntries;
	
	public void insert(T object) {
		
		String sqlCommandFormat = String.format("insert into %s (%s) values (%s)", tableName, columnNames, interrogationMarks);
		
		try (Connection dbConnection = ConexaoBD.getConexaoBD()){
			PreparedStatement sqlCommand = dbConnection.prepareStatement(sqlCommandFormat);
			fillInsertStatement(sqlCommand, object);
			
			sqlCommand.execute();
			sqlCommand.close();
		}
		
		catch(SQLException e) {
			System.out.println("Exception: " + e.getMessage());
			throw new RuntimeException(e);
		}
	}
	
	//remove pelo id
	public void delete(T object) {
		
		String sqlCommandFormat = String.format("delete from %s where %s.Id=?", tableName, tableName);
		
		try (Connection dbConnection = ConexaoBD.getConexaoBD()){
			
			PreparedStatement sqlCommand = dbConnection.prepareStatement(sqlCommandFormat);
			fillDeleteStatement(sqlCommand, object);
			
			sqlCommand.execute();
			sqlCommand.close();
		}
		
		catch(SQLException e) {
			System.out.println("Exception: " + e.getMessage());
			throw new RuntimeException(e);
		}
	}
	
	//modifica a entrada da tabela usando o id da entrada
	public void update( T object) {
		
		String sqlCommandFormat = String.format("update %s set %s where %s.Id=?",
				tableName, formatForModifyingColumnsWhenUpdatingTableEntries, tableName);

		try (Connection dbConnection = ConexaoBD.getConexaoBD()){
			
			PreparedStatement sqlCommand = dbConnection.prepareStatement(sqlCommandFormat);
			fillUpdateStatement(sqlCommand, object);

			sqlCommand.execute();
			sqlCommand.close();
		}
		
		catch(SQLException e) {
			System.out.println("Exception: " + e.getMessage());
			throw new RuntimeException(e);
		}
	}

	public <L extends java.util.List<T>> void selectToList( L list ) {
		String sqlCommandFormat = String.format("select * from %s", tableName);

		try (Connection dbConnection = ConexaoBD.getConexaoBD()){
			PreparedStatement sqlCommand = dbConnection.prepareStatement(sqlCommandFormat);
			ResultSet querySet = sqlCommand.executeQuery();

			while(querySet.next()) {
				T object = getObjectWithDbInformation(querySet);
				list.add(object);
			}

			querySet.close();
			sqlCommand.close();

			sqlCommand.close();
		} catch(SQLException e) {
			System.out.println("Exception: " + e.getMessage());
			throw new RuntimeException(e);
		}
	}

	public <L extends java.util.List<T>> void selectToList(L list, String predicate) {
		String sqlCommandFormat = String.format("select * from %s where %s", tableName, predicate);

		try (Connection dbConnection = ConexaoBD.getConexaoBD()){
			PreparedStatement sqlCommand = dbConnection.prepareStatement(sqlCommandFormat);
			ResultSet querySet = sqlCommand.executeQuery();

			while(querySet.next()) {
				T object = getObjectWithDbInformation(querySet);
				list.add(object);
			}

			querySet.close();
			sqlCommand.close();

			sqlCommand.close();
		} catch(SQLException e) {
			System.out.println("Exception: " + e.getMessage());
			throw new RuntimeException(e);
		}
	}

	abstract protected void fillInsertStatement( PreparedStatement statement, T object ) throws SQLException;
	abstract protected void fillDeleteStatement( PreparedStatement statement, T object ) throws SQLException;
	abstract protected void fillUpdateStatement( PreparedStatement statement, T object ) throws SQLException;
	abstract protected T getObjectWithDbInformation( ResultSet set ) throws SQLException;
}