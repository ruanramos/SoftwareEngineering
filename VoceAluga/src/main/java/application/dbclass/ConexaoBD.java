package application.dbclass;

import java.sql.*;

public class ConexaoBD {
	
	private static String user = "testuser";	//usuario do BD
	private static String password = "123test";	//senha do BD
	private static String schema = "testdb"; //nome do schema no mySQL
	private static String serverName = "localhost"; //caminho do servidor do BD
	
	private static String statusDaConexao = "";
	
	public static Connection getConexaoBD() throws SQLException {

		Connection conexao = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = String.format("jdbc:mysql://%s/%s?useTimezone=true&serverTimezone=UTC&user=%s&password=%s",
					serverName, schema, user, password);

			conexao = DriverManager.getConnection(url);
			
			setStatusDaConexao("Connection opened");
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
			throw e;
		}
		catch(ClassNotFoundException e) {
			System.out.println("Exception:" + e.getMessage());
		}
		catch(Exception e) {
			System.out.println("Exception:" + e.getMessage());
		}
		finally {}
		
		return conexao;
	}
	
	public static void FecharConexao() throws SQLException {
		 
        try {
 
            ConexaoBD.getConexaoBD().close();
 
        } catch (SQLException e) {
        	
        	throw e;
        }
	}
	
	public static java.sql.Connection ReiniciarConexao() throws SQLException {
		 
		FecharConexao();

		return ConexaoBD.getConexaoBD();
	}

	public static String getStatusDaConexao() {
		return statusDaConexao;
	}

	private static void setStatusDaConexao(String statusDaConexao) {
		ConexaoBD.statusDaConexao = statusDaConexao;
	}
}
