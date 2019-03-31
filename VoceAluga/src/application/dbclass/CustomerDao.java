package application.dbclass;

import application.dbclass.*;
import application.model.*;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class CustomerDao extends DefaultDao<Customer> {
	
	public CustomerDao() {
		tableName = "Customer";
		columnNames = "Cpf,FirstName,LastName,Rg,Cnh,Birthday,Cellphone,Address,Email";
		interrogationMarks = "?,?,?,?,?,?,?,?,?"; //mesmo número de interrogações que de colunas em 'columnNames'
		formatForModifyingColumnsWhenUpdatingTableEntries =
				"Cpf=?,FirstName=?,LastName=?,Rg=?,Cnh=?,Birthday=?,Cellphone=?,Address=?,Email=?";
	}
	
	protected void fillInsertStatement(PreparedStatement statement, Customer customer) throws SQLException {
		statement.setString(1, customer.getCpf());
		statement.setString(2, customer.getFirstName());
		statement.setString(3, customer.getLastName());
		statement.setString(4, customer.getRg());
		statement.setString(5, customer.getCnh());
		statement.setDate(6, Date.valueOf(customer.getBirthday()));
		statement.setString(7, customer.getCellphone());
		statement.setString(8, customer.getAddress());
		statement.setString(9, customer.getEmail());
	}
	
	protected void fillDeleteStatement(PreparedStatement statement, Customer customer) throws SQLException {
		statement.setInt(1, customer.getId());
	}

	protected void fillUpdateStatement(PreparedStatement statement, Customer customer) throws SQLException {
		statement.setString(1, customer.getCpf());
		statement.setString(2, customer.getFirstName());
		statement.setString(3, customer.getLastName());
		statement.setString(4, customer.getRg());
		statement.setString(5, customer.getCnh());
		statement.setDate(6, Date.valueOf(customer.getBirthday()));
		statement.setString(7, customer.getCellphone());
		statement.setString(8, customer.getAddress());
		statement.setString(9, customer.getEmail());
		statement.setString(10, String.valueOf(customer.getId()));
	}
	
	protected Customer getObjectWithDbInformation(ResultSet set) throws SQLException{
		Customer customer = new Customer();

		customer.setId(set.getInt("Id"));
		customer.setCpf(set.getString("Cpf"));
		customer.setFirstName(set.getString("FirstName"));
		customer.setLastName(set.getString("LastName"));
		customer.setRg(set.getString("Rg"));
		customer.setBirthday(set.getDate("Birthday").toLocalDate());
		customer.setCellphone(set.getString("Cellphone"));
		customer.setAddress(set.getString("Address"));
		customer.setEmail(set.getString("Email"));

		return customer;
	}
}
