package application.dbclass;

import application.dbclass.*;
import application.model.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDao extends DefaultDao<Customer> {
	
	public CustomerDao() {
		tableName = "Cliente";
		columnNames = "cpf,primeiro_nome,ultimo_nome,telefone,endereco,email";
		interrogationMarks = "?,?,?,?,?,?"; //mesmo número de interrogações que de colunas em 'columnNames'
		formatForModifyingColumnsWhenUpdatingTableEntries = "cpf=?,primeiro_nome=?,ultimo_nome=?,telefone=?,endereco=?,email=?";
	}
	
	protected void fillInsertStatement( PreparedStatement statement , Customer customer) throws SQLException {
		statement.setString(1, customer.getCpf());
		statement.setString(2, customer.getFirstName());
		statement.setString(3, customer.getLastName());
		statement.setString(4, customer.getCellphone());
		statement.setString(5, customer.getAddress());
		statement.setString(6, customer.getEmail());
	}
	
	protected void fillDeleteStatement( PreparedStatement statement , Customer customer) throws SQLException {
		// Customer nao tem campo id
//		statement.setInt(1, customer.getId());
	}

	protected void fillUpdateStatement(PreparedStatement statement, Customer customer) throws SQLException {
		statement.setString(1, customer.getCpf());
		statement.setString(2, customer.getFirstName());
		statement.setString(3, customer.getLastName());
		statement.setString(4, customer.getCellphone());
		statement.setString(5, customer.getAddress());
		statement.setString(6, customer.getEmail());
//		statement.setInt(7, customer.getId());
	}
	
	protected Customer getObjectWithDbInformation( ResultSet set ) throws SQLException{
		
		Customer cliente = new Customer();
		
//		cliente.setId(set.getInt("idCliente"));
		cliente.setCpf(set.getString("cpf"));
		cliente.setFirstName(set.getString("primeiro_nome"));
		cliente.setLastName(set.getString("ultimo_nome"));
		cliente.setCellphone(set.getString("telefone"));
		cliente.setAddress(set.getString("endereco"));
		cliente.setEmail(set.getString("email"));
		
		return cliente;
	}
}
